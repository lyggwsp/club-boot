package com.sgqn.club.base.service.auth;

import com.sgqn.club.base.constant.CommonStatusEnum;
import com.sgqn.club.base.constant.LoginLogTypeEnum;
import com.sgqn.club.base.constant.LoginResultEnum;
import com.sgqn.club.base.dto.convert.auth.AuthConvert;
import com.sgqn.club.base.dto.req.auth.AuthLoginReq;
import com.sgqn.club.base.dto.resp.auth.AuthLoginResp;
import com.sgqn.club.base.entity.*;
import com.sgqn.club.base.exception.UserException;
import com.sgqn.club.base.service.club.ClubService;
import com.sgqn.club.base.service.log.LoginLogService;
import com.sgqn.club.base.service.permisson.SysRoleService;
import com.sgqn.club.base.service.user.SysUserService;
import com.sgqn.club.base.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public AuthLoginResp login(AuthLoginReq authLoginReq) {
        // 无验证码,不需要校验
        // 使用账号密码登录
        SysUser sysUser = authenticate(authLoginReq.getUsername(),
                authLoginReq.getPassword(), authLoginReq.getRoleId(), authLoginReq.getClubId());
        return createTokenAfterLoginSuccess(sysUser.getId(), sysUser.getUsername(),
                authLoginReq.getRoleId(), authLoginReq.getClubId(), LoginLogTypeEnum.LOGIN_USERNAME);
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
            throw UserException.AUTH_LOGIN_USER_DISABLED;
        }
        if (!sysUserService.isPasswordMatch(password, user.getPassword())) {
            createLoginLog(user.getId(), username, roleId, clubId, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw UserException.AUTH_LOGIN_USER_DISABLED;
        }
        // 是否禁用
        if (user.getStatus().equals(CommonStatusEnum.DISABLE.getType())) {
            createLoginLog(user.getId(), username, roleId, clubId, logTypeEnum, LoginResultEnum.USER_DISABLED);
            throw UserException.USER_NOT_FOUND_EXCEPTION;
        }
        // 校验社团
        Club club = clubService.getRoleFromCache(clubId);
        if (club == null || club.getStatus().equals(CommonStatusEnum.DISABLE.getType())) {
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
     * 登录成功后创建并返回token
     *
     * @param userId   用户ID
     * @param username 用户名
     * @param roleId   角色编号
     * @param clubId   社团编号
     * @param logType  登录类型
     * @return 认证结果
     */
    private AuthLoginResp createTokenAfterLoginSuccess(Long userId, String username, Long roleId, Long clubId,
                                                       LoginLogTypeEnum logType) {
        // 插入登陆日志
        createLoginLog(userId, username, roleId, clubId, logType, LoginResultEnum.SUCCESS);
        //  创建访问令牌
        AuthToken accessToken = tokenService.createAccessToken(userId, roleId, clubId);
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
        loginLogService.createLogin(log);
    }
}
