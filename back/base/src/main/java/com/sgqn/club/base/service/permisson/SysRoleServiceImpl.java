package com.sgqn.club.base.service.permisson;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.club.base.constant.CommonStatusEnum;
import com.sgqn.club.base.constant.SysRoleCodeEnum;
import com.sgqn.club.base.constant.SysRoleTypeEnum;
import com.sgqn.club.base.dto.condition.SysRoleCondition;
import com.sgqn.club.base.dto.convert.permission.SysRoleConvert;
import com.sgqn.club.base.entity.SysRole;
import com.sgqn.club.base.entity.SysRoleMenu;
import com.sgqn.club.base.exception.SysRoleException;
import com.sgqn.club.base.mapper.SysRoleMapper;
import com.sgqn.club.base.mq.producer.permission.SysRoleProducer;
import com.sgqn.club.base.service.permisson.PermissionService;
import com.sgqn.club.base.service.permisson.SysRoleService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {

    /**
     * 角色缓存
     * key：角色编号 {@link SysRole#getId()}
     * <p>
     * 这里声明 volatile 修饰的原因是，每次刷新时，直接修改指向
     */
    @Getter
    private volatile Map<Long, SysRole> roleCache;


    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private PermissionService sysRoleMenuService;

    @Autowired
    private SysRoleProducer sysRoleProducer;

    /**
     * 初始化 {@link #roleCache} 缓存
     */
    @Override
    @PostConstruct
    public void initLocalCache() {
        List<SysRole> roleList = sysRoleMapper.selectList(null);
        log.info("[initLocalCache][缓存角色，数量为:{}]", roleList.size());
        // 第二步：构建缓存
        roleCache = SysRoleConvert.do2Map(roleList, SysRole::getId);
    }

    /**
     * {@inheritDoc}
     *
     * @param page             简单分页模型
     * @param sysRoleCondition 分页查询条件
     * @return
     */
    @Override
    public IPage<SysRole> getByCondition(Page<SysRole> page, SysRoleCondition sysRoleCondition) {
        return sysRoleMapper.selectByCondition(page, sysRoleCondition);
    }

    /**
     * {@inheritDoc}
     *
     * @param roleIds 角色ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeSysRoleBatch(List<Long> roleIds) {
        roleIds.forEach(this::removeSysRoleById);
        sysRoleProducer.sendRoleRefreshMessage();
    }

    /**
     * {@inheritDoc}
     *
     * @param id 角色ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeSysRoleById(Long id) {
        // 1、校验角色信息
        validateRoleForUpdate(id);
        // 2、删除角色信息
        this.removeById(id);
        // 3、删除相关菜单权限信息
        LambdaQueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<SysRoleMenu>().lambda()
                .eq(SysRoleMenu::getRoleId, id);
        sysRoleMenuService.remove(wrapper);
        // TODO 发送刷新消息
        // 发送刷新消息. 注意，需要事务提交后，在进行发送刷新消息。不然 db 还未提交，结果缓存先刷新了
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {

            @Override
            public void afterCommit() {
                sysRoleProducer.sendRoleRefreshMessage();
            }

        });
    }

    /**
     * {@inheritDoc}
     *
     * @param roleId   角色ID
     * @param disabled boolean 类型是否启用  实际 0 表示禁用，1表示启用
     * @return
     */
    @Override
    public void updateRoleStatus(Long roleId, Boolean disabled) {
        // 1、校验待角色信息
        validateRoleForUpdate(roleId);
        // 2、更新角色状态
        LambdaUpdateWrapper<SysRole> updateWrapper = new UpdateWrapper<SysRole>().lambda()
                .eq(SysRole::getId, roleId).set(SysRole::getStatus,
                        disabled ? CommonStatusEnum.ENABLE.getType() : CommonStatusEnum.DISABLE.getType());
        // TODO 发送刷新消息
        this.update(updateWrapper);
        sysRoleProducer.sendRoleRefreshMessage();
    }

    /**
     * {@inheritDoc}
     *
     * @param sysRole 待更新的角色信息
     * @return
     */
    @Override
    public void updateRole(@NotNull SysRole sysRole) {
        // 1、校验角色是否可以更新
        validateRoleForUpdate(sysRole.getId());
        // 2、校验角色的唯一字段是否重复
        validateDuplicateSysRole(sysRole.getName(), sysRole.getCode(), sysRole.getId());
        this.updateById(sysRole);
        sysRoleProducer.sendRoleRefreshMessage();
    }

    /**
     * {@inheritDoc}
     *
     * @param sysRole 角色信息
     * @return
     */
    @Override
    @Transactional
    public Long saveRole(@NotNull SysRole sysRole) {
        // 1、校验角色信息
        validateDuplicateSysRole(sysRole.getName(), sysRole.getCode(), null);
        // 2、插入数据到角色表中
        sysRole.setType(SysRoleTypeEnum.CUSTOM.getType());
        this.save(sysRole);
        // 发送刷新消息. 注意，需要事务提交后，在进行发送刷新消息。不然 db 还未提交，结果缓存先刷新了
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {

            @Override
            public void afterCommit() {
                sysRoleProducer.sendRoleRefreshMessage();
            }

        });
        return sysRole.getId();
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<SysRole> getRoleList() {
        return sysRoleMapper.selectList(null);
    }

    /**
     * {@inheritDoc}
     *
     * @param roleList 角色数组
     * @return
     */
    @Override
    public boolean hasAnySuperAdmin(Collection<SysRole> roleList) {
        if (CollectionUtil.isEmpty(roleList)) {
            return false;
        }
        return roleList.stream().anyMatch(role -> SysRoleCodeEnum.isSuperAdmin(role.getCode()));
    }

    /**
     * {@inheritDoc}
     *
     * @param ids 角色编号数组
     * @return
     */
    @Override
    public List<SysRole> getRoleList(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<SysRole> roleList = getRoleList();
        return roleList.stream().filter(role -> ids.contains(role.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public SysRole getRoleFromCache(Long id) {
        return roleCache.get(id);
    }

    @Override
    public List<SysRole> getRoleListFromCache(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return roleCache.values().stream().filter(roleDO -> ids.contains(roleDO.getId()))
                .collect(Collectors.toList());
    }


    /**
     * 校验角色是否可以更新
     *
     * @param id 角色信息
     */
    void validateRoleForUpdate(Long id) {
        SysRole sysRole = this.getById(id);
        // 1、校验角色是否存在
        if (ObjectUtil.isEmpty(sysRole)) {
            throw SysRoleException.ROLE_NOT_FOUND_EXCEPTION;
        }
        // 2、校验角色是否为内置角色
        if (SysRoleTypeEnum.SYSTEM.getType().equals(sysRole.getType())) {
            throw SysRoleException.ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE;
        }
    }

    /**
     * 校验角色的唯一字段是否重复
     * 1、是否具有相同的角色名
     * 2、是否具有相同编码的角色
     *
     * @param roleName 角色名
     * @param roleCode 角色编码
     * @param id       角色ID
     */
    void validateDuplicateSysRole(String roleName, String roleCode, Long id) {
        //1、超级管理员，不允许创建
        if (SysRoleCodeEnum.isSuperAdmin(roleCode)) {
            throw SysRoleException.ROLE_ADMIN_CODE_ERROR;
        }
        //2、该角色的名字是否被占用
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getName, roleName);
        SysRole role = this.getOne(queryWrapper);
        if (!ObjectUtil.isEmpty(role) && !role.getId().equals(id)) {
            throw SysRoleException.ROLE_NAME_DUPLICATE;
        }
        //3、是否存在编码相通的角色信息
        if (!StringUtils.hasText(roleCode)) {
            return;
        }
        // 该 code 编码被其他角色占用
        LambdaQueryWrapper<SysRole> codeWrapper = new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getCode, roleCode);
        role = this.getOne(codeWrapper);
        if (!ObjectUtil.isEmpty(role) && !role.getId().equals(id)) {
            throw SysRoleException.ROLE_CODE_DUPLICATE;
        }
    }

}
