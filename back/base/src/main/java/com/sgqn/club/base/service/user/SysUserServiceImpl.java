package com.sgqn.club.base.service.user;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.club.base.constant.CommonStatusEnum;
import com.sgqn.club.base.constant.SysRoleEnum;
import com.sgqn.club.base.dto.convert.permission.PermissionConvert;
import com.sgqn.club.base.dto.convert.user.UserConvert;
import com.sgqn.club.base.dto.req.user.SysUserReq;
import com.sgqn.club.base.entity.Club;
import com.sgqn.club.base.entity.SysRole;
import com.sgqn.club.base.entity.SysUser;
import com.sgqn.club.base.entity.SysUserRoleClub;
import com.sgqn.club.base.exception.ClubException;
import com.sgqn.club.base.exception.SysRoleException;
import com.sgqn.club.base.exception.UserException;
import com.sgqn.club.base.mapper.SysUserMapper;
import com.sgqn.club.base.service.club.ClubService;
import com.sgqn.club.base.service.permisson.SysRoleService;
import com.sgqn.club.base.service.permisson.SysUserRoleClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
     * @param sysUser 待创建的用户
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
        SysUserRoleClub sysUserRoleClub = PermissionConvert.req2do(sysUserReq);
        int insert = sysUserMapper.insert(sysUser);
        sysUserRoleClubService.save(sysUserRoleClub);
        return insert > 0 ? sysUser.getId() : insert;
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
