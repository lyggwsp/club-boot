package com.sgqn.club.base.service.user;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.club.base.constant.CommonStatusEnum;
import com.sgqn.club.base.constant.SysRoleEnum;
import com.sgqn.club.base.dto.convert.permission.PermissionConvert;
import com.sgqn.club.base.dto.convert.user.UserConvert;
import com.sgqn.club.base.dto.req.user.SysUserDetailReq;
import com.sgqn.club.base.dto.req.user.SysUserReq;
import com.sgqn.club.base.entity.*;
import com.sgqn.club.base.exception.ClubException;
import com.sgqn.club.base.exception.SysRoleException;
import com.sgqn.club.base.exception.UserException;
import com.sgqn.club.base.mapper.SysUserMapper;
import com.sgqn.club.base.service.club.ClubService;
import com.sgqn.club.base.service.permisson.PermissionService;
import com.sgqn.club.base.service.permisson.SysRoleService;
import com.sgqn.club.base.service.permisson.SysUserRoleClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClubService clubService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleClubService sysUserRoleClubService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public SysUser getByUserName(String username) {
        return sysUserMapper.selectByUsername(username);
    }

    /**
     * {@inheritDoc}
     *
     * @param rawPassword     原始密码
     * @param encodedPassword 对比的密码
     * @return
     */
    @Override
    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * {@inheritDoc}
     *
     * @param sysUserReq 待创建的用户
     * @return
     */
    @Override
    public Long createUser(SysUserReq sysUserReq) {
        // 1、校验用户名是否重复
        if (!(this.getByUserName(sysUserReq.getUsername()) == null)) {
            throw UserException.USERNAME_EXISTS;
        }
        // 2、校验社团
        if (ObjectUtil.isNotNull(sysUserReq.getClubId())) {
            Club club = clubService.getById(sysUserReq.getClubId());
            if (club == null || club.getStatus().equals(CommonStatusEnum.DISABLE.getType())) {
                throw ClubException.CLUB_NOT_FOUND_EXCEPTION;
            }
        }
        // 3、校验角色(如果角色为空需要直接赋予普通角色)
        if (ObjectUtil.isNull(sysUserReq.getRoleId())) {
            sysUserReq.setRoleId(SysRoleEnum.ORDINARY_USER.getRoleId());
        } else {
            SysRole sysRole = sysRoleService.getById(sysUserReq.getRoleId());
            if (sysRole == null || sysRole.getStatus().equals(CommonStatusEnum.DISABLE.getType())) {
                throw SysRoleException.ROLE_NOT_FOUND_EXCEPTION;
            }
        }
        // 4、插入用户信息
        SysUser sysUser = UserConvert.req2do(sysUserReq);
        // 加密密码
        sysUser.setPassword(encodePassword(sysUser.getPassword()));
        // 默认开启状态
        if (ObjectUtil.isEmpty(sysUser.getStatus())) {
            sysUser.setStatus(CommonStatusEnum.ENABLE.getType());
        }
        SysUserRoleClub sysUserRoleClub = PermissionConvert.req2do(sysUserReq);
        int insert = sysUserMapper.insert(sysUser);
        sysUserRoleClubService.save(sysUserRoleClub);
        return insert > 0 ? sysUser.getId() : insert;
    }


    /**
     * {@inheritDoc}
     *
     * @param userId           用户编号
     * @param sysUserDetailReq 用户详细信息请求实体
     */
    @Override
    @Transactional
    public void creatUserDetails(Long userId, SysUserDetailReq sysUserDetailReq) {
        SysUser sysUser = structureCreateOrUpdate(userId, sysUserDetailReq.getNickname(), sysUserDetailReq.getEmail());
        UserDetail userDetail = UserConvert.req2do(sysUserDetailReq);
        // 1、保存用户详细信息
        userDetailService.save(userDetail);
        Long detailId = userDetail.getId();
        // 2、关联用户表信息
        if (detailId != null) {
            sysUser.setDetailId(detailId);
            // 3、保存用户表中的用户详细ID
            sysUserMapper.updateById(sysUser);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param userId           用户编号
     * @param sysUserDetailReq 用户详细信息请求实体
     */
    @Override
    @Transactional
    public void updateUserDetails(Long userId, SysUserDetailReq sysUserDetailReq) {
        // 1、校验邮箱、获取用户登录表实体
        SysUser sysUser = structureCreateOrUpdate(userId, sysUserDetailReq.getNickname(), sysUserDetailReq.getEmail());
        UserDetail userDetail = UserConvert.req2do(sysUserDetailReq);
        // 2、更新用户基本表信息
        userDetailService.updateById(userDetail);
        // 3、更新用户登录表实体信息
        sysUserMapper.updateById(sysUser);
    }

    @Override
    public void updateUserStatus(Long id, Integer status) {
        // 校验用户是否存在
        validateUserExists(id);
        // 更新状态
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setStatus(status);
        sysUserMapper.updateById(sysUser);
    }

    /**
     * {@inheritDoc}
     *
     * @param id 用户编号
     */
    @Override
    public void deleteUser(Long id) {
        // 1、校验用户是否存在
        validateUserExists(id);
        // 2、 删除用户
        sysUserMapper.deleteById(id);
        // 3、删除相关联的其他信息
        permissionService.processUserDeleted(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param id       用户编号
     * @param password 密码
     */
    @Override
    public void updateUserPassword(Long id, String password) {
        // 校验用户是否存在
        validateUserExists(id);
        // 更新密码
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setPassword(encodePassword(password)); // 加密密码
        sysUserMapper.updateById(sysUser);
    }

    /**
     * 校验用户是否存在
     *
     * @param id 用户编号
     */
    private void validateUserExists(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null) {
            throw UserException.USER_NOT_FOUND_EXCEPTION;
        }
    }

    /**
     * 校验邮箱，同时返回登录用户表信息
     *
     * @param userId   用户编号
     * @param nickName 用户昵称
     * @param email    邮箱
     * @return 登录用户表信息实体
     */
    private SysUser structureCreateOrUpdate(Long userId, String nickName, String email) {
        // 1、获取用户登录表信息
        SysUser sysUser = sysUserMapper.selectById(userId);
        if (sysUser == null) {
            throw UserException.USER_NOT_FOUND_EXCEPTION;
        }
        // 2、校验邮箱是否重复
        SysUser byEmail = sysUserMapper.selectByEmail(email);
        if (byEmail != null && !byEmail.getId().equals(userId)) {
            throw UserException.EMAIL_EXISTS;
        }
        // 3、设置邮箱、昵称
        sysUser.setNickname(nickName);
        sysUser.setEmail(email);
        return sysUser;
    }

    /**
     * 对密码进行加密
     *
     * @param password 密码
     * @return 加密后的密码
     */
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
