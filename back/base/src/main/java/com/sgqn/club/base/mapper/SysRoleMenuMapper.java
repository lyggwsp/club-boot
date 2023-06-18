package com.sgqn.club.base.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sgqn.club.base.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

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
     * 根据菜单编号删除角色对于的权限信息
     *
     * @param menuId 菜单编号
     */
    default void deleteListByMenuId(Long menuId) {
        this.delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getMenuId, menuId));
    }
}
