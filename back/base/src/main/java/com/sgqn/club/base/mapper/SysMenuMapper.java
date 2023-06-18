package com.sgqn.club.base.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sgqn.club.base.entity.SysMenu;
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
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据父菜单编号和菜单名获取菜单信息
     *
     * @param parentId 父菜单编号
     * @param name     菜单名
     * @return 返回查找到的菜单项
     */
    default SysMenu selectByParentIdAndName(Long parentId, String name) {
        return selectOne(new LambdaQueryWrapper<SysMenu>()
                .eq(SysMenu::getParentId, parentId).eq(SysMenu::getName, name));
    }

}
