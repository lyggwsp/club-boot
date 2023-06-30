package com.sgqn.club.base.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgqn.club.base.dto.req.user.SysUserDetailReq;
import com.sgqn.club.base.dto.req.user.SysUserReq;
import com.sgqn.club.base.entity.SysUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
public interface SysUserService extends IService<SysUser> {


    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    SysUser getByUserName(String username);


    /**
     * 密码对比
     *
     * @param rawPassword     原始密码
     * @param encodedPassword 对比的密码
     * @return 是否相同
     */
    boolean isPasswordMatch(String rawPassword, String encodedPassword);


    /**
     * 创建新用户
     *
     * @param sysUserReq 待创建的用户
     * @return 用户ID
     */
    Long createUser(SysUserReq sysUserReq);


    /**
     * 创建用户详细信息
     *
     * @param userId           用户编号
     * @param sysUserDetailReq 用户详细信息请求实体
     */
    void creatUserDetails(Long userId, SysUserDetailReq sysUserDetailReq);


    /**
     * 修改用户详细信息
     *
     * @param userId           用户编号
     * @param sysUserDetailReq 用户详细信息请求实体
     */
    void updateUserDetails(Long userId, SysUserDetailReq sysUserDetailReq);


    /**
     * 更新角色状态
     *
     * @param id     用户编号
     * @param status 状态值
     */
    void updateUserStatus(Long id, Integer status);


}
