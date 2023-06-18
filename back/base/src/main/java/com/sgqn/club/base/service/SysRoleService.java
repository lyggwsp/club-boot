package com.sgqn.club.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sgqn.club.base.dto.condition.SysRoleCondition;
import com.sgqn.club.base.dto.resp.SysRoleResp;
import com.sgqn.club.base.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 根据角色信息中不为空的信息进行条件查询
     *
     * @param page             简单分页模型
     * @param sysRoleCondition 分页查询条件
     * @return 返回查询到的信息
     */
    IPage<SysRole> getByCondition(Page<SysRole> page, SysRoleCondition sysRoleCondition);

    /**
     * 批量删除角色信息，同时角色菜单表中的信息
     *
     * @param roleIds 角色ID
     * @return 是否删除成功
     */
    boolean removeSysRoleBatch(List<Long> roleIds);

    /**
     * 根据角色ID删除角色信息
     *
     * @param id 角色ID
     * @return 返回是否删除成功
     */
    boolean removeSysRoleById(Long id);

    /**
     * 更新角色状态
     *
     * @param roleId   角色ID
     * @param disabled boolean 类型是否启用  实际 0 表示禁用，1表示启用
     * @return 返回是否更新状态成功
     */
    boolean updateRoleStatus(Long roleId, Boolean disabled);

    /**
     * 更新角色信息
     *
     * @param sysRole 待更新的角色信息
     * @return 返回是否更新成功
     */
    boolean updateRole(SysRole sysRole);

    /**
     * 新增角色信息
     *
     * @param sysRole 角色信息
     * @return 返回是否新增成功
     */
    boolean saveRole(SysRole sysRole);

    /**
     * 获取角色列表
     *
     * @return 全部角色信息
     */
    List<SysRole> getRoleList();


}
