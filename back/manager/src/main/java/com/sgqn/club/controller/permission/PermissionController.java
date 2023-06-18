package com.sgqn.club.controller.permission;

import com.sgqn.club.base.bean.ResultBean;
import com.sgqn.club.base.dto.req.permission.permission.PermissionAssignRoleMenuReq;
import com.sgqn.club.base.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * 权限 Controller，提供赋予用户、角色的权限的 API 接口
 *
 * @author wspstart
 * @description
 * @create 2023-06-18 15:13
 */
@RestController
@RequestMapping("/system/permission")
@Api(tags = "管理后台 - 权限[role-menu]")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/list-role-resources/{id}")
    @Parameter(name = "roleId", description = "角色编号", required = true)
    @ApiOperation("获取角色拥有的菜单编号[listRoleMenu]")
    public ResultBean<Set<Long>> listRoleMenu(@PathVariable("id") Long roleId) {
        return ResultBean.success("获取信息成功",
                permissionService.getRoleMenuIds(roleId));
    }

    @PostMapping("/assign-role-menu")
    @ApiOperation("赋予角色菜单[assignRoleMenu]")
    public ResultBean<?> assignRoleMenu(@RequestBody PermissionAssignRoleMenuReq permissionAssignRoleMenuReq) {
        return ResultBean.error("功能待实现");
    }

    @PostMapping("/assign-role-data-scope")
    @ApiOperation("赋予角色数据权限[assignRoleDataScope]")
    public ResultBean<?> assignRoleDataScope() {
        return ResultBean.error("功能待实现");
    }

    @GetMapping("/list-user-roles")
    @ApiOperation("获取管理员拥有的角色编号[assignRoleDataScope]")
    public ResultBean<?> listAdminRoles() {
        return ResultBean.error("功能待实现");
    }


    @PostMapping("/assign-user-role")
    @ApiOperation("赋予用户角色[assignUserRole]")
    public ResultBean<?> assignUserRole() {
        return ResultBean.error("功能待实现");
    }


}
