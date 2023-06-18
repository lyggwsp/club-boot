package com.sgqn.club.base.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.club.base.entity.SysMenu;
import com.sgqn.club.base.entity.SysRoleMenu;
import com.sgqn.club.base.mapper.SysRoleMenuMapper;
import com.sgqn.club.base.service.PermissionService;
import com.sgqn.club.base.service.SysMenuService;
import com.sgqn.club.base.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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

    /**
     * {@inheritDoc}
     *
     * @param roleId 角色Id
     * @return
     */
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

    /**
     * {@inheritDoc}
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID列表
     */
    @Override
    @Transactional
    public void assignRoleMenu(Long roleId, Set<Long> menuIds) {
        // 1、获取角色拥有的菜单
        Set<Long> dbMenuIds = sysRoleMenuMapper.selectListByRoleId(roleId).stream()
                .map(SysRoleMenu::getId).filter(Objects::nonNull).collect(Collectors.toSet());
        // 2、计算新增和删除的菜单编号
        Collection<Long> createMenuIds = CollectionUtil.subtract(menuIds, dbMenuIds);
        Collection<Long> deleteMenuIds = CollectionUtil.subtract(dbMenuIds, menuIds);
        // 3、执行删除和新增，对于已经授权的菜单不需要进行处理
        if (!CollectionUtil.isEmpty(createMenuIds)) {
            List<SysRoleMenu> save = createMenuIds.stream().map(i ->
                    SysRoleMenu.builder().roleId(roleId).menuId(i).build()).collect(Collectors.toList());
            this.saveBatch(save);
        }
        if (!CollectionUtil.isEmpty(deleteMenuIds)) {
            sysRoleMenuMapper.deleteListByRoleIdAndMenuIds(roleId, deleteMenuIds);
        }
    }
}
