package com.sgqn.club.base.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.club.base.dto.convert.permission.PermissionConvert;
import com.sgqn.club.base.dto.req.permission.permission.PermissionAssignUserClubRoleReq;
import com.sgqn.club.base.entity.SysMenu;
import com.sgqn.club.base.entity.SysRoleMenu;
import com.sgqn.club.base.entity.SysUserRoleClub;
import com.sgqn.club.base.exception.ClubException;
import com.sgqn.club.base.exception.DepartmentException;
import com.sgqn.club.base.exception.SysRoleException;
import com.sgqn.club.base.exception.UserException;
import com.sgqn.club.base.mapper.DepartmentMapper;
import com.sgqn.club.base.mapper.SysRoleMenuMapper;
import com.sgqn.club.base.mapper.SysUserRoleClubMapper;
import com.sgqn.club.base.service.*;
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

    @Autowired
    private SysUserRoleClubMapper sysUserRoleClubMapper;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private DepartmentMapper departmentMapper;

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

    /**
     * {@inheritDoc}
     *
     * @param userId 用户编号
     * @return
     */
    @Override
    public Set<Long> getUserRoleIdListByUserId(Long userId) {
        return sysUserRoleClubMapper.selectListByUserId(userId).stream()
                .map(SysUserRoleClub::getId).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     *
     * @param userId 用户编号
     * @param clubId 社团编号
     * @param roleId 角色编号
     */
    @Override
    public void assignUserClubRole(PermissionAssignUserClubRoleReq reqVo) {
        // 校验用户、社团、角色是否存在
        validateUserClubRole(reqVo.getUserId(),
                reqVo.getClubId(), reqVo.getRoleId(), reqVo.getDeptId());
        // 插入数据到数据
        SysUserRoleClub sysUserRoleClub = PermissionConvert.req2do(reqVo);
        sysUserRoleClubMapper.insert(sysUserRoleClub);
    }


    /**
     * 校验赋予用户角色字段是否合法
     * 1、用户必须存在
     * 2、社团必须存在
     * 3、角色必须存在
     * 4、社团部门必须存在
     *
     * @param userId 用户编号
     * @param clubId 社团编号
     * @param roleId 角色编号
     * @param deptId 部门编号
     */
    void validateUserClubRole(Long userId, Long clubId, Long roleId, Long deptId) {
        // 校验用户编号
        if (sysUserService.getById(userId) == null) {
            throw UserException.USER_NOT_FOUND_EXCEPTION;
        }
        // 校验社团编号
        if (clubService.getById(clubId) == null) {
            throw ClubException.CLUB_NOT_FOUND_EXCEPTION;
        }
        // 校验角色编号
        if (sysRoleService.getById(roleId) == null) {
            throw SysRoleException.ROLE_NOT_FOUND_EXCEPTION;
        }
        // 校验部门
        if (deptId != null && departmentMapper.selectByClubIdAndDepartId(clubId, deptId) == null) {
            throw DepartmentException.DEPARTMENT_NOT_FOUND_EXCEPTION;
        }
    }
}
