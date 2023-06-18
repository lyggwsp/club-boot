package com.sgqn.club.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.club.base.entity.SysMenu;
import com.sgqn.club.base.entity.SysRoleMenu;
import com.sgqn.club.base.mapper.SysRoleMenuMapper;
import com.sgqn.club.base.service.PermissionService;
import com.sgqn.club.base.service.SysMenuService;
import com.sgqn.club.base.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限（角色菜单）服务实现类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements PermissionService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * {@inheritDoc}
     *
     * @param menuId 菜单编号
     */
    @Override
    public void processMenuDeleted(Long menuId) {
        sysRoleMenuMapper.deleteListByMenuId(menuId);
    }

    @Override
    public Set<Long> getRoleMenuIds(Long roleId) {
        // 如果是管理员的情况下，获取全部菜单编号
        if (sysRoleService.hasAnySuperAdmin(Collections.singleton(roleId))) {
            return sysMenuService.getMenuList().stream()
                    .map(SysMenu::getId).filter(Objects::nonNull).collect(Collectors.toSet());
        }
        // 如果是非管理员的情况下，获得拥有的菜单编号
        return sysRoleMenuMapper.selectListByRoleId(roleId).stream()
                .map(SysRoleMenu::getId).filter(Objects::nonNull).collect(Collectors.toSet());
    }
}
