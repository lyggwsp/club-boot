package com.sgqn.club.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.club.base.constant.SysMenuTypeEnum;
import com.sgqn.club.base.entity.SysMenu;
import com.sgqn.club.base.exception.SysMenuException;
import com.sgqn.club.base.mapper.SysMenuMapper;
import com.sgqn.club.base.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static com.sgqn.club.base.entity.SysMenu.ID_ROOT;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * {@inheritDoc}
     *
     * @param sysMenu 新增的菜单
     * @return
     */
    @Override
    public Long createMenu(SysMenu sysMenu) {
        // 1、校验父菜单是否存在
        validateParentMenu(sysMenu.getParentId(), null);
        // 2、校验菜单（自己）
        validateMenu(sysMenu.getParentId(), sysMenu.getName(), null);
        // 3、向表中插入数据
        int insert = sysMenuMapper.insert(sysMenu);
        // 4、返回新增后的菜单ID
        return insert > 0 ? sysMenu.getId() : insert;
    }

    /**
     * {@inheritDoc}
     *
     * @param sysMenu 菜单信息
     */
    @Override
    public void updateMenu(SysMenu sysMenu) {

    }


    /**
     * {@inheritDoc}
     *
     * @param id 菜单编号
     */
    @Override
    public void deleteMenu(Long id) {

    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<SysMenu> getMenuList() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param sysMenu 筛选条件请求 VO
     * @return
     */
    @Override
    public List<SysMenu> getMenuList(SysMenu sysMenu) {
        return null;
    }


    /**
     * {@inheritDoc}
     *
     * @param menuTypes     菜单类型数组
     * @param menusStatuses 菜单状态数组
     * @return
     */
    @Override
    public List<SysMenu> getMenuListFromCache(Collection<Integer> menuTypes, Collection<Integer> menusStatuses) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param menuIds       菜单编号数组
     * @param menuTypes     菜单类型数组
     * @param menusStatuses 菜单状态数组
     * @return
     */
    @Override
    public List<SysMenu> getMenuListFromCache(Collection<Long> menuIds, Collection<Integer> menuTypes, Collection<Integer> menusStatuses) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param permission 权限标识
     * @return
     */
    @Override
    public List<SysMenu> getMenuListByPermissionFromCache(String permission) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param id 菜单编号
     * @return
     */
    @Override
    public SysMenu getMenu(Long id) {
        return null;
    }


    /**
     * 校验父菜单是否合法
     * <p>
     * 1、不能设置自己为父菜单
     * 2、父菜单不存在
     * 3、父菜单必须是菜单类型
     *
     * @param parentId 父菜单编号
     * @param childId  当前菜单编号
     */
    void validateParentMenu(Long parentId, Long childId) {
        // 判断创建的是否是目录
        if (parentId == null || ID_ROOT.equals(parentId)) {
            return;
        }
        // 不能设置自己为父菜单
        if (parentId.equals(childId)) {
            throw SysMenuException.MENU_PARENT_ROOT;
        }
        //校验父菜单是否存在
        SysMenu menu = this.getById(parentId);
        if (menu == null) {
            throw SysMenuException.MENU_PARENT_NOT_EXISTS;
        }
        // 父菜单必须是目录或者菜单类型
        if (!SysMenuTypeEnum.DIR.getType().equals(menu.getMenuType())
                && !SysMenuTypeEnum.MENU.getType().equals(menu.getMenuType())) {
            throw SysMenuException.MENU_PARENT_NOT_DIR_OR_MENU;
        }
    }

    /**
     * 校验菜单是否合法
     * 1、校验相同父菜单编号下，是否存在相同的菜单名
     *
     * @param parentId 父菜单编号
     * @param name     菜单名称
     * @param id       菜单编号
     */
    void validateMenu(Long parentId, String name, Long id) {
        // 获取父菜单
        SysMenu menu = sysMenuMapper.selectByParentIdAndName(parentId, name);

        if (menu == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否相同 id 的菜单
        if (id == null) {
            throw SysMenuException.MENU_NAME_DUPLICATE;
        }

        if (!menu.getId().equals(id)) {
            throw SysMenuException.MENU_NAME_DUPLICATE;
        }

    }
}
