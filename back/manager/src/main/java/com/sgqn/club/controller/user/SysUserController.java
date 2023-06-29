package com.sgqn.club.controller.user;

import com.sgqn.club.base.bean.ResultBean;
import com.sgqn.club.base.dto.req.user.SysUserReq;
import com.sgqn.club.base.service.user.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wspstart
 * @description
 * @create 2023-06-13 21:48
 */
@RestController
@RequestMapping("/sys-user")
@Api(tags = "管理后台 - 用户[user-controller]")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/create")
    @ApiOperation(value = "新增用户")
    public ResultBean<?> createUser(SysUserReq sysUserReq) {

        return ResultBean.error("功能未实现");
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改用户")
    public ResultBean<?> updateUser() {
        return ResultBean.error("功能未实现");
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除用户")
    public ResultBean<?> deleteUser() {
        return ResultBean.error("功能未实现");
    }

    @PatchMapping("/update-password")
    @ApiOperation(value = "更新用户密码")
    public ResultBean<?> updateUserPassword() {
        return ResultBean.error("功能未实现");
    }

    @PatchMapping("/update-status")
    @ApiOperation(value = "修改用户状态")
    public ResultBean<?> updateUserStatus() {
        return ResultBean.error("功能未实现");
    }

    @GetMapping("/page")
    @ApiOperation(value = "获得用户分页列表")
    public ResultBean<?> getUserPage() {
        return ResultBean.error("功能未实现");
    }

    @GetMapping("/list-all-simple")
    @ApiOperation(value = "获取用户精简信息列表",consumes = "只包含被开启的用户，主要用于前端的下拉选项")
    public ResultBean<?> getSimpleUsers() {
        return ResultBean.error("功能未实现");
    }

    @GetMapping("/get")
    @ApiOperation(value = "获得用户详情")
    public ResultBean<?> getInfo() {
        return ResultBean.error("功能未实现");
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出用户")
    public ResultBean<?> exportUsers() {
        return ResultBean.error("功能未实现");
    }

    @GetMapping("/get-import-template")
    @ApiOperation(value = "获得导入用户模板")
    public ResultBean<?> importTemplate() {
        return ResultBean.error("功能未实现");
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入用户")
    public ResultBean<?> importExcel() {
        return ResultBean.error("功能未实现");
    }
}
