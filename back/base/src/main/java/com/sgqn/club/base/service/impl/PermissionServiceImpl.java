package com.sgqn.club.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.club.base.entity.SysRoleMenu;
import com.sgqn.club.base.mapper.SysRoleMenuMapper;
import com.sgqn.club.base.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  权限（角色菜单）服务实现类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements PermissionService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public void processMenuDeleted(Long menuId) {
        sysRoleMenuMapper.deleteListByMenuId(menuId);
    }
}
