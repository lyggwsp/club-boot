package com.sgqn.club.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.club.base.entity.SysRole;
import com.sgqn.club.base.entity.SysRoleMenu;
import com.sgqn.club.base.mapper.SysRoleMapper;
import com.sgqn.club.base.service.SysRoleMenuService;
import com.sgqn.club.base.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * {@inheritDoc}
     *
     * @param roleIds 角色ID
     * @return
     */
    @Override
    @Transactional
    public boolean removeSysRoleBatch(List<Long> roleIds) {
        // 1、删除角色信息
        boolean removeRoles = this.removeByIds(roleIds);
        if (!removeRoles) {
            return false;
        }
        // 2、删除角色菜单中间表对应的信息
        LambdaQueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<SysRoleMenu>().lambda()
                .in(SysRoleMenu::getRoleId, roleIds);
        return sysRoleMenuService.remove(wrapper);
    }

    /**
     * {@inheritDoc}
     *
     * @param roleId   角色ID
     * @param disabled boolean 类型是否启用  实际 0 表示禁用，1表示启用
     * @return
     */
    @Override
    public boolean updateRoleStatus(Long roleId, Boolean disabled) {
        int state = disabled ? 1 : 0;
        LambdaUpdateWrapper<SysRole> updateWrapper = new UpdateWrapper<SysRole>().lambda()
                .eq(SysRole::getId, roleId).set(SysRole::getStatus, state);
        return this.update(updateWrapper);
    }
}
