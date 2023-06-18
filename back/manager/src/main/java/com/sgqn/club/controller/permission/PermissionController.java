package com.sgqn.club.controller.permission;

import com.sgqn.club.base.bean.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/list-role-resources")
    @ApiOperation("获取角色拥有的菜单编号[listRoleMenu]")
    public ResultBean<Set<Long>> listRoleMenu(Long roleId) {
        return ResultBean.error("功能待实现");
    }

    @PostMapping("/assign-role-menu")
    @ApiOperation("赋予角色菜单[assignRoleMenu]")
    public ResultBean<?> assignRoleMenu() {
        return ResultBean.error("功能待实现");
    }

    @PostMapping("/assign-role-data-scope")
    @ApiOperation("赋予角色数据权限[assignRoleDataScope]")
    public ResultBean<?> assignRoleDataScope() {
        return ResultBean.error("功能待实现");
    }

    @PostMapping("/list-user-roles")
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
