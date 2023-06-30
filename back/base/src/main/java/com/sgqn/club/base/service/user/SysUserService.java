package com.sgqn.club.base.service.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sgqn.club.base.dto.condition.SysUserCondition;
import com.sgqn.club.base.dto.req.user.SysUserDetailReq;
import com.sgqn.club.base.dto.req.user.SysUserReq;
import com.sgqn.club.base.entity.ClubRoleUserPage;
import com.sgqn.club.base.entity.SysUser;

import java.util.List;

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

    /**
     * 删除用户信息
     *
     * @param id 用户编号
     */
    void deleteUser(Long id);


    /**
     * 修改用户密码
     *
     * @param id       用户编号
     * @param password 密码
     */
    void updateUserPassword(Long id, String password);


    /**
     * 分页查询获取用户-角色-社团-部门信息
     *
     * @param sysUserCondition 分页查询条件
     * @return IPage
     */
    IPage<ClubRoleUserPage> getPage(IPage<ClubRoleUserPage> page, SysUserCondition sysUserCondition);


}
