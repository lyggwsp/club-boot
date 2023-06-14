package com.sgqn.club.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
     * 批量删除角色信息，同时角色菜单表中的信息
     *
     * @param roleIds 角色ID
     * @return 是否删除成功
     */
    boolean removeSysRoleBatch(List<Long> roleIds);


    /**
     * 更新角色状态
     *
     * @param roleId   角色ID
     * @param disabled boolean 类型是否启用  实际 0 表示禁用，1表示启用
     * @return 返回是否更新状态成功
     */
    boolean updateRoleStatus(Long roleId, Boolean disabled);


}
