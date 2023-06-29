package com.sgqn.club.base.service.auth;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.sgqn.club.base.constant.CommonStatusEnum;
import com.sgqn.club.base.constant.LoginLogTypeEnum;
import com.sgqn.club.base.constant.LoginResultEnum;
import com.sgqn.club.base.constant.SysMenuTypeEnum;
import com.sgqn.club.base.dto.convert.auth.AuthConvert;
import com.sgqn.club.base.dto.req.auth.AuthLoginReq;
import com.sgqn.club.base.dto.resp.auth.AuthLoginResp;
import com.sgqn.club.base.entity.*;
import com.sgqn.club.base.exception.UserException;
import com.sgqn.club.base.service.club.ClubService;
import com.sgqn.club.base.service.log.LoginLogService;
import com.sgqn.club.base.service.permisson.PermissionService;
import com.sgqn.club.base.service.permisson.SysRoleService;
import com.sgqn.club.base.service.user.SysUserService;
import com.sgqn.club.base.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.sgqn.club.base.constant.RedisConstant.TOKEN_STORE;

/**
 * @author wspstart
 * @description
 * @create 2023-06-22 0:17
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 根据用户获取社团角色信息 --> 点击登录 --> 根据用户名获取用户信息 ---> 存在的话需要获取权限信息存储到redis中
    // 前端携带token访问后端获取菜单信息接口 --> 获取菜单信息中解析token 获取当前角色对应的角色菜单信息
    @Override
    public AuthLoginResp login(AuthLoginReq authLoginReq) {
        // 无验证码,不需要校验
        // 使用账号密码登录
        SysUser sysUser = authenticate(authLoginReq.getUsername(),
                authLoginReq.getPassword(), authLoginReq.getRoleId(), authLoginReq.getClubId());
        return createTokenAfterLoginSuccess(sysUser.getId(), sysUser.getUsername(),
                authLoginReq.getRoleId(), authLoginReq.getClubId());
    }

    /**
     * {@inheritDoc}
     *
     * @param username 用户名
     * @param password 用户密码
     * @param roleId
     * @param clubId
     * @return
     */
    @Override
    public SysUser authenticate(String username, String password, Long roleId, Long clubId) {
        // 账号是否存在
        final LoginLogTypeEnum logTypeEnum = LoginLogTypeEnum.LOGIN_USERNAME;
        SysUser user = sysUserService.getByUserName(username);
        if (user == null) {
            createLoginLog(null, username, roleId, clubId, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw UserException.USER_NOT_FOUND_EXCEPTION;
        }
        if (!sysUserService.isPasswordMatch(password, user.getPassword())) {
            createLoginLog(user.getId(), username, roleId, clubId, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw UserException.AUTH_LOGIN_BAD_CREDENTIALS;
        }
        // 是否禁用
        if (user.getStatus().equals(CommonStatusEnum.DISABLE.getType())) {
            createLoginLog(user.getId(), username, roleId, clubId, logTypeEnum, LoginResultEnum.USER_DISABLED);
            throw UserException.AUTH_LOGIN_USER_DISABLED;
        }
        // 校验社团
        Club club = clubService.getClubFromCache(clubId);
        if (club == null || club.getStatus().equals(CommonStatusEnum.DISABLE.getType()) || ObjectUtil.equal(club.getId(), club)) {
            createLoginLog(user.getId(), username, roleId, clubId, logTypeEnum, LoginResultEnum.USER_DISABLED);
            throw UserException.USER_NOT_FOUND_EXCEPTION;
        }
        // 校验角色
        SysRole role = sysRoleService.getRoleFromCache(roleId);
        if (role == null || role.getStatus().equals(CommonStatusEnum.DISABLE.getType())) {
            createLoginLog(user.getId(), username, roleId, clubId, logTypeEnum, LoginResultEnum.USER_DISABLED);
            throw UserException.USER_NOT_FOUND_EXCEPTION;
        }
        return user;
    }

    /**
     * {@inheritDoc}
     *
     * @param authToken 用户
     */
    @Override
    public void logout(AuthToken authToken) {
        // 1、清空redis缓存中的登录的信息
        stringRedisTemplate.delete(TOKEN_STORE + authToken.getUserId());
        // 2、记录退出日志
        SysUser user = sysUserService.getById(authToken.getUserId());
        createLoginLog(authToken.getUserId(), user.getUsername(), authToken.getRoleId(), authToken.getClubId(),
                LoginLogTypeEnum.LOGIN_USERNAME, LoginResultEnum.LOGOUT_SELF);
    }

    /**
     * 登录成功后创建并返回token
     *
     * @param userId   用户ID
     * @param username 用户名
     * @param roleId   角色编号
     * @param clubId   社团编号
     * @return 认证结果
     */
    private AuthLoginResp createTokenAfterLoginSuccess(Long userId, String username, Long roleId, Long clubId) {
        // 插入登陆日志
        createLoginLog(userId, username, roleId, clubId, LoginLogTypeEnum.LOGIN_USERNAME, LoginResultEnum.SUCCESS);
        //  创建访问令牌
        AuthToken accessToken = tokenService.createAccessToken(userId, roleId, clubId);
        // 存储权限信息到redis中
        List<String> permissions = permissionService.getRoleMenuList(roleId, Convert.toSet(Integer.class, SysMenuTypeEnum.BUTTON.getType()),
                        Convert.toSet(Integer.class, CommonStatusEnum.ENABLE.getType())).stream()
                .map(SysMenu::getPermission)
                .collect(Collectors.toList());
        // 获取角色信息
        String roleCode = sysRoleService.getRoleFromCache(roleId).getCode();
        String data = String.join(",", permissions) + ",ROLE_" + roleCode;
        stringRedisTemplate.opsForValue().set(TOKEN_STORE + userId, data, Duration.ofDays(1));
        // 构建返回结果
        return AuthConvert.do2Resp(accessToken);
    }

    /**
     * 创建一条日志
     *
     * @param userId      用户编号
     * @param username    用户名
     * @param roleId      角色编号
     * @param clubId      社团名
     * @param logTypeEnum 登录类型
     * @param loginResult 登录结果
     */
    private void createLoginLog(Long userId, String username, Long roleId, Long clubId,
                                LoginLogTypeEnum logTypeEnum, LoginResultEnum loginResult) {
        LoginLog log = LoginLog.builder().logType(logTypeEnum.getType()).userId(userId).username(username)
                .roleId(roleId).clubId(clubId).userAgent(ServletUtils.getUserAgent())
                .userIp(ServletUtils.getClientIp()).result(loginResult.getResult()).build();
        /*
        本地访问存储的IP为 0:0:0:0:0:0:0:1
        普及:
        IP地址"0:0:0:0:0:0:0:1"是IPv6的本地环回地址，也被称为"localhost"或"::1"。
        在Java中，当使用IPv6环境时，如果获取到的IP地址为"0:0:0:0:0:0:0:1"，则表示请求是从本地发起的，也即请求来自于同一台机器上的应用程序。
         */
        loginLogService.createLogin(log);
    }

}
