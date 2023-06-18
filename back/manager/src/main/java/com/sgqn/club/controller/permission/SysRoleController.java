package com.sgqn.club.controller.permission;

import cn.hutool.core.lang.Opt;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgqn.club.base.bean.ResultBean;
import com.sgqn.club.base.dto.convert.permission.SysRoleConvert;
import com.sgqn.club.base.dto.condition.SysRoleCondition;
import com.sgqn.club.base.dto.req.permission.role.SysRoleReq;
import com.sgqn.club.base.dto.resp.permission.SysRoleExcelResp;
import com.sgqn.club.base.dto.resp.permission.SysRoleResp;
import com.sgqn.club.base.entity.SysRole;
import com.sgqn.club.base.exception.SysRoleException;
import com.sgqn.club.base.service.SysRoleService;
import com.sgqn.club.base.utils.ExcelUtils;
import com.sgqn.club.base.validation.ValidGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
@Api(tags = "管理后台 - 角色[sys-role]")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @DeleteMapping("/delete-id")
    @ApiOperation("删除单个角色[deleteById]")
    public ResultBean<?> deleteById(@RequestHeader("X-Role-Ids")
                                    @ApiParam(name = "角色ID", required = true) Long id) {
        sysRoleService.removeSysRoleById(id);
        return ResultBean.success("删除成功");
    }

    @GetMapping(value = "/export-role", produces = "application/vnd.ms-excel;charset=UTF-8")
    @ApiOperation("导出角色信息")
    public void exportRoleInfo(HttpServletResponse response) throws IOException {

        List<SysRoleExcelResp> data = SysRoleConvert.do2resp(sysRoleService.getRoleList());
        // 输出
        ExcelUtils.write(response, "role_data.xls", "角色列表", SysRoleExcelResp.class, data);

    }

    @PatchMapping(value = "/update-status")
    @ApiOperation("更新角色状态[updateStatus]")
    public ResultBean<?> updateStatus(@RequestParam @ApiParam(name = "角色ID", required = true) Long id,
                                      @RequestParam @ApiParam(name = "是否启用", required = true) Boolean disabled) {
        boolean updateRoleStatus = sysRoleService.updateRoleStatus(id, disabled);
        return updateRoleStatus ? ResultBean.success("更新成功")
                : ResultBean.error("服务器异常，更新状态失败!");
    }

    @PutMapping("/update")
    @ApiOperation("更新[update]")
    public ResultBean<?> update(
            @RequestBody @Validated({ValidGroup.Update.class}) SysRoleReq sysRoleReq) {
        SysRole sysRole = SysRoleConvert.req2do(sysRoleReq);
        boolean update = sysRoleService.updateRole(sysRole);
        return update ? ResultBean.success("更新成功！")
                : ResultBean.error("服务器异常，更新失败!");
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询[getById]")
    public ResultBean<?> getById(@PathVariable Long id) {
        Opt.ofNullable(id).orElseThrow(() -> SysRoleException.ID_NULL);
        SysRole sysRole = sysRoleService.getById(id);
        if (!ObjectUtil.isEmpty(sysRole)) {
            SysRoleResp respRole = SysRoleConvert.do2resp(sysRole);
            return ResultBean.success("查询成功", respRole);
        }
        return ResultBean.error("查询失败");
    }


    @DeleteMapping("/delete")
    @ApiOperation("批量删除[delete]")
    @Deprecated
    public ResultBean<?> delete(@RequestHeader("X-Role-Ids") List<Long> roleIdList) {
        if (roleIdList.isEmpty()) {
            throw SysRoleException.ID_NULL;
        }
        boolean remove = sysRoleService.removeSysRoleBatch(roleIdList);
        return remove ? ResultBean.success("删除成功！") : ResultBean.error("删除失败");
    }


    @PostMapping("/save")
    @ApiOperation("新增[save]")
    public ResultBean<String> save(
            @RequestBody @Validated({ValidGroup.Insert.class}) SysRoleReq sysRoleReq) {
        SysRole sysRole = SysRoleConvert.req2do(sysRoleReq);
        boolean save = sysRoleService.saveRole(sysRole);
        if (save) {
            return ResultBean.success("角色新增成功");
        } else {
            return ResultBean.error("服务器异常角色新增失败");
        }
    }


    @PostMapping("/condition")
    @ApiOperation("分页条件查询[getByCondition]")
    public ResultBean<IPage<SysRoleResp>> getByCondition(SysRoleCondition condition) {
        Page<SysRole> page = condition.getPage();
        if (page.getSize() != 0) {
            IPage<SysRole> ipage = sysRoleService.getByCondition(page, condition);
            IPage<SysRoleResp> respPage = SysRoleConvert.do2resp(ipage);
            return ResultBean.success("查询成功", respPage);
        }
        return ResultBean.notFound("无角色信息");
    }


}
