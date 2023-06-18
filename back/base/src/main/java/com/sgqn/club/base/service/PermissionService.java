package com.sgqn.club.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgqn.club.base.entity.SysRoleMenu;

import java.util.Set;

/**
 * <p>
 * 权限（角色菜单）的 服务类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
public interface PermissionService extends IService<SysRoleMenu> {


    /**
     * 处理菜单删除时，删除关联授权数据
     *
     * @param menuId 菜单编号
     */
    void processMenuDeleted(Long menuId);

    /**
     * 获取角色拥有的菜单
     *
     * @param roleId 角色Id
     * @return 返回角色拥有的菜单列表
     */
    Set<Long> getRoleMenuIds(Long roleId);

    /**
     * 给角色分配菜单
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID列表
     */
    void assignRoleMenu(Long roleId, Set<Long> menuIds);
}
