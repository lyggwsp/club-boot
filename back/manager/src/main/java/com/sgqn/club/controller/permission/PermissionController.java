package com.sgqn.club.controller.permission;

import com.sgqn.club.base.bean.ResultBean;
import com.sgqn.club.base.dto.req.permission.permission.PermissionAssignRoleMenuReq;
import com.sgqn.club.base.dto.req.permission.permission.PermissionAssignUserClubRoleReq;
import com.sgqn.club.base.service.PermissionService;
import com.sgqn.club.base.validation.ValidGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    public ResultBean<Set<String>> listRoleMenu(@PathVariable("id") Long roleId) {
        return ResultBean.success("信息获取成功",
                permissionService.getRoleMenuIds(roleId));
    }

    @PostMapping("/assign-role-menu")
    @ApiOperation("赋予角色菜单[assignRoleMenu]")
    public ResultBean<?> assignRoleMenu(@RequestBody PermissionAssignRoleMenuReq permissionAssignRoleMenuReq) {
        permissionService.assignRoleMenu(permissionAssignRoleMenuReq.getRoleId(),
                permissionAssignRoleMenuReq.getMenuIds());
        return ResultBean.success("赋予角色菜单列表权限成功");
    }

    @GetMapping("/list-user-roles")
    @ApiOperation("获取管理员拥有的角色编号列表[assignRoleDataScope]")
    public ResultBean<?> listAdminRoles(Long userId) {
        return ResultBean.success("信息获取成功",
                permissionService.getUserRoleIdListByUserId(userId));
    }


    @PostMapping("/assign-user-role")
    @ApiOperation("赋予用户角色[assignUserRole]")
    public ResultBean<?> assignUserRole(@RequestBody @Validated({ValidGroup.Insert.class})
                                        PermissionAssignUserClubRoleReq reqVo) {
        permissionService.assignUserClubRole(reqVo);
        return ResultBean.error("赋予角色信息成功");
    }


}
