package com.sgqn.club.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgqn.club.base.dto.condition.SysMenuCondition;
import com.sgqn.club.base.entity.SysMenu;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 初始化菜单的本地缓存
     */
    void initLocalCache();

    /**
     * 创建系统菜单
     *
     * @param sysMenu 新增的菜单
     * @return 新增成功的菜单ID，新增失败返回-1
     */
    Long createMenu(SysMenu sysMenu);


    /**
     * 更新菜单
     *
     * @param sysMenu 菜单信息
     */
    void updateMenu(SysMenu sysMenu);

    /**
     * 删除菜单
     *
     * @param id 菜单编号
     */
    void deleteMenu(Long id);

    /**
     * 获得所有菜单列表
     *
     * @return 菜单列表
     */
    List<SysMenu> getMenuList();



    /**
     * 筛选菜单列表
     *
     * @param sysMenuCondition 筛选条件请求 VO
     * @return 菜单列表
     */
    List<SysMenu> getMenuList(SysMenuCondition sysMenuCondition);

    /**
     * 获得所有菜单
     * <p>
     * 任一参数为空时，则返回为空
     *
     * @param menuTypes     菜单类型数组
     * @param menusStatuses 菜单状态数组
     * @return 菜单列表
     */
    List<SysMenu> getMenuListFromCache(Collection<Integer> menuTypes, Collection<Integer> menusStatuses);

    /**
     * 获得指定编号的菜单数组
     * <p>
     * 任一个参数为空时，则返回为空
     *
     * @param menuIds       菜单编号数组
     * @param menuTypes     菜单类型数组
     * @param menusStatuses 菜单状态数组
     * @return 菜单数组
     */
    List<SysMenu> getMenuListFromCache(Collection<Long> menuIds, Collection<Integer> menuTypes,
                                       Collection<Integer> menusStatuses);

    /**
     * 获得权限对应的菜单数组
     *
     * @param permission 权限标识
     * @return 数组
     */
    List<SysMenu> getMenuListByPermissionFromCache(String permission);

    /**
     * 获得菜单
     *
     * @param id 菜单编号
     * @return 菜单
     */
    SysMenu getMenu(Long id);
}
