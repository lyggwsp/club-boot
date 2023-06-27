package com.sgqn.club.base.service.permisson;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.sgqn.club.base.constant.SysMenuTypeEnum;
import com.sgqn.club.base.dto.condition.SysMenuCondition;
import com.sgqn.club.base.entity.SysMenu;
import com.sgqn.club.base.exception.SysMenuException;
import com.sgqn.club.base.mapper.SysMenuMapper;
import com.sgqn.club.base.mq.producer.permission.SysMenuProducer;
import com.sgqn.club.base.service.permisson.PermissionService;
import com.sgqn.club.base.service.permisson.SysMenuService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

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
@Slf4j
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;


    @Autowired
    private PermissionService permissionService;

    /**
     * 菜单缓存
     * key：菜单编号
     * <p>
     * 这里声明 volatile 修饰的原因是，每次刷新时，直接修改指向
     */
    @Getter
    @Setter
    private volatile Map<Long, SysMenu> menuCache;

    /**
     * 权限与菜单缓存
     * key：权限 {@link SysMenu#getPermission()}
     * value：MenuDO 数组，因为一个权限可能对应多个 MenuDO 对象
     * <p>
     * 这里声明 volatile 修饰的原因是，每次刷新时，直接修改指向
     */
    @Getter
    @Setter
    private volatile Multimap<String, SysMenu> permissionMenuCache;

    @Autowired
    private SysMenuProducer sysMenuProducer;

    @Override
    @PostConstruct
    public void initLocalCache() {
        // 第一步：查询数据
        List<SysMenu> menuList = this.getMenuList();
        log.info("[initLocalCache][缓存菜单，数量为:{}]", menuList.size());

        // 第二步：构建缓存
        ImmutableMap.Builder<Long, SysMenu> menuCacheBuilder = ImmutableMap.builder();
        ImmutableMultimap.Builder<String, SysMenu> permMenuCacheBuilder = ImmutableMultimap.builder();
        menuList.forEach(menuDO -> {
            menuCacheBuilder.put(menuDO.getId(), menuDO);
            if (StrUtil.isNotEmpty(menuDO.getPermission())) { // 会存在 permission 为 null 的情况，导致 put 报 NPE 异常
                permMenuCacheBuilder.put(menuDO.getPermission(), menuDO);
            }
        });
        menuCache = menuCacheBuilder.build();
        permissionMenuCache = permMenuCacheBuilder.build();
    }

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
        sysMenuMapper.insert(sysMenu);
        // 4、返回新增后的菜单ID
        // 发送刷新消息
        sysMenuProducer.sendMenuRefreshMessage();
        return sysMenu.getId();
    }

    /**
     * {@inheritDoc}
     *
     * @param sysMenu 菜单信息
     */
    @Override
    public void updateMenu(SysMenu sysMenu) {
        // 1、校验菜单是否存在
        if (this.getById(sysMenu.getId()) == null) {
            throw SysMenuException.MENU_NOT_EXISTS;
        }
        // 2、校验父菜单是否存在
        validateParentMenu(sysMenu.getParentId(), sysMenu.getId());
        // 3、 校验自己
        validateMenu(sysMenu.getParentId(), sysMenu.getName(), sysMenu.getId());
        // 4、更新到数据库
        this.updateById(sysMenu);
        // 发送刷新消息
        sysMenuProducer.sendMenuRefreshMessage();
    }


    /**
     * {@inheritDoc}
     *
     * @param id 菜单编号
     */
    @Override
    @Transactional
    public void deleteMenu(Long id) {
        // 1、校验是否还有子菜单
        if (sysMenuMapper.selectCountByParentId(id) > 0) {
            throw SysMenuException.MENU_EXISTS_CHILDREN;
        }
        //2、校验删除的菜单是否存在
        if (this.getById(id) == null) {
            throw SysMenuException.MENU_NOT_EXISTS;
        }
        //3、删除菜单信息
        this.removeById(id);
        //4、删除授予给角色的权限
        permissionService.processMenuDeleted(id);
        // 发送刷新消息. 注意，需要事务提交后，在进行发送刷新消息。不然 db 还未提交，结果缓存先刷新了
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {

            @Override
            public void afterCommit() {
                sysMenuProducer.sendMenuRefreshMessage();
            }

        });
    }

    @Override
    public List<SysMenu> getMenuList() {
        return this.getMenuList(SysMenuCondition.builder().build());
    }


    /**
     * {@inheritDoc}
     *
     * @param sysMenuCondition 筛选条件请求 VO
     * @return
     */
    @Override
    public List<SysMenu> getMenuList(SysMenuCondition sysMenuCondition) {
        return sysMenuMapper.selectList(sysMenuCondition);
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
        // 任一一个参数为空，则返回空
        if (CollectionUtil.isEmpty(menuTypes) || CollectionUtil.isEmpty(menusStatuses)) {
            return Collections.emptyList();
        }
        // 创建新数组，避免缓存被修改
        return menuCache.values().stream().filter(menu -> menuTypes.contains(menu.getMenuType())
                        && menusStatuses.contains(menu.getMenuType()))
                .collect(Collectors.toList());
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
        // 任一一个参数为空，则返回空
        if (CollectionUtil.isEmpty(menuIds) ||
                CollectionUtil.isEmpty(menuIds) || CollectionUtil.isEmpty(menusStatuses)) {
            return Collections.emptyList();
        }
        return menuCache.values().stream().filter(menu -> menuIds.contains(menu.getId())
                        && menuTypes.contains(menu.getMenuType())
                        && menusStatuses.contains(menu.getRenderMenu()))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     *
     * @param permission 权限标识
     * @return
     */
    @Override
    public List<SysMenu> getMenuListByPermissionFromCache(String permission) {
        return new ArrayList<>(permissionMenuCache.get(permission));
    }

    /**
     * {@inheritDoc}
     *
     * @param id 菜单编号
     * @return
     */
    @Override
    public SysMenu getMenu(Long id) {
        return this.getById(id);
    }


    /**
     * 校验父菜单是否合法<br></br>
     * <p>
     * 1、不能设置自己为父菜单<br></br>
     * 2、父菜单不存在<br></br>
     * 3、父菜单必须是菜单类型<br></br>
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
     * 校验菜单是否合法<br/>
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
        // 如果 id 为空，说明不用比较是否相同 id 的菜单 （对于新增来说，证明菜单已经存在了）
        if (id == null) {
            throw SysMenuException.MENU_NAME_DUPLICATE;
        }

        if (!menu.getId().equals(id)) {
            throw SysMenuException.MENU_NAME_DUPLICATE;
        }

    }
}
