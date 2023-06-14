package com.sgqn.club.controller;

import cn.hutool.core.lang.Opt;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgqn.club.base.bean.ResultBean;
import com.sgqn.club.base.dto.condition.SysRoleCondition;
import com.sgqn.club.base.dto.convert.SysRoleConvert;
import com.sgqn.club.base.dto.req.SysRoleReq;
import com.sgqn.club.base.dto.resp.SysRoleResp;
import com.sgqn.club.base.entity.SysRole;
import com.sgqn.club.base.exception.SysRoleException;
import com.sgqn.club.base.service.SysRoleService;
import com.sgqn.club.base.validation.ValidGroup;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
@RestController
@RequestMapping("/sys-role")
@Slf4j
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;



    @PutMapping
    @ApiOperation("更新[update]")
    public ResultBean<?> update(
            @RequestBody @Validated({ValidGroup.Update.class}) SysRoleReq sysRoleReq) {
        SysRole sysRole = SysRoleConvert.req2do(sysRoleReq);
        sysRole = sysRoleService.getById(sysRole.getId());
        if (ObjectUtil.isEmpty(sysRole)) {
            throw SysRoleException.RoleNotFoundException;
        }
        boolean update = sysRoleService.updateById(sysRole);
        return update ? ResultBean.success("更新成功！")
                : ResultBean.error("服务器异常，更新失败!");
    }

    @GetMapping
    @ApiOperation("根据ID查询[getById]")
    public ResultBean<?> getById(Long id) {
        Opt.ofNullable(id).orElseThrow(() -> SysRoleException.ID_NULL);
        SysRole sysRole = sysRoleService.getById(id);
        if (!ObjectUtil.isEmpty(sysRole)) {
            SysRoleResp respRole = SysRoleConvert.do2resp(sysRole);
            return ResultBean.success("查询成功", respRole);
        }
        return ResultBean.error("查询失败");
    }


    @DeleteMapping
    @ApiOperation("(批量)删除[delete]")
    public ResultBean<?> delete(@RequestBody List<Long> roleIdList) {
        if (roleIdList.isEmpty()) {
            throw SysRoleException.ID_NULL;
        }
        boolean remove = sysRoleService.removeSysRoleBatch(roleIdList);
        return remove ? ResultBean.success("删除成功！") : ResultBean.error("删除失败");
    }


    @PostMapping
    @ApiOperation("新增[save]")
    public ResultBean<String> save(
            @RequestBody @Validated({ValidGroup.Insert.class}) SysRoleReq sysRoleReq) {
        SysRole sysRole = SysRoleConvert.req2do(sysRoleReq);
        boolean save = sysRoleService.save(sysRole);
        if (save) {
            return ResultBean.success("角色新增成功");
        } else {
            return ResultBean.error("服务器异常角色新增失败");
        }
    }


    @PostMapping("/condition")
    @ApiOperation("分页条件查询[getByCondition]")
    public ResultBean<IPage<SysRoleResp>> getByCondition(SysRoleCondition condition) {
        Page<SysRole> page = sysRoleService.page(condition.getPage());
        if (page.getSize() != 0) {
            IPage<SysRoleResp> respPage = SysRoleConvert.do2resp(page);
            return ResultBean.success("查询成功", respPage);
        }
        return ResultBean.notFound("无角色信息");
    }


}
