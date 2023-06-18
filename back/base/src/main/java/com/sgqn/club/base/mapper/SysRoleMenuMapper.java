package com.sgqn.club.base.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sgqn.club.base.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {


    /**
     * 根据角色ID和菜单ID删除角色菜单表中信息
     *
     * @param roleId        角色编号
     * @param deleteMenuIds 待删除的菜单编号列表
     */
    default void deleteListByRoleIdAndMenuIds(Long roleId, Collection<Long> deleteMenuIds) {
        this.delete(new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getRoleId, roleId)
                .in(SysRoleMenu::getMenuId, deleteMenuIds));
    }

    /**
     * 根据菜单编号删除角色对于的权限信息
     *
     * @param menuId 菜单编号
     */
    default void deleteListByMenuId(Long menuId) {
        this.delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getMenuId, menuId));
    }

    /**
     * 根据角色编号查询该角色具有的角色菜单
     *
     * @param roleId 角色编号
     * @return 角色菜单列表
     */
    default List<SysRoleMenu> selectListByRoleId(Long roleId) {
        return this.selectList(new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getRoleId, roleId));
    }
}
