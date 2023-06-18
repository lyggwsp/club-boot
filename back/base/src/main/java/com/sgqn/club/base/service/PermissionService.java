package com.sgqn.club.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgqn.club.base.entity.SysRoleMenu;

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
}
