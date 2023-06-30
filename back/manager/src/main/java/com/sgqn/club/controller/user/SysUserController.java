package com.sgqn.club.controller.user;

import com.sgqn.club.base.bean.ResultBean;
import com.sgqn.club.base.dto.req.user.SysUserDetailReq;
import com.sgqn.club.base.dto.req.user.SysUserReq;
import com.sgqn.club.base.entity.AuthToken;
import com.sgqn.club.base.service.user.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.sgqn.club.security.SecurityFrameworkUtils.getAuthToken;

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

    /*
        后台管理-用户模块
        管理员可以创建用户--> 只填写sysUser表信息 --> 填写用户名、密码、角色、社团编号等信息
        管理员可修改用户必要信息 ---> sysUser表信息 --> 这个修改是权限管理，需要由权限模块将赋予用户社团角色权限接口实现
        管理员可修改用户状态
        在前端需要提供个人信息模块，在这个模块中，用户可以对个人信息进行修改、可以修改密码、邮箱、用户详情表信息

     */
    @PostMapping("/create")
    @ApiOperation(value = "新增用户")
    public ResultBean<?> createUser(SysUserReq sysUserReq) {
        Long userId = sysUserService.createUser(sysUserReq);
        return ResultBean.success("新增成功", userId);
    }

    @PutMapping("/update-details")
    @ApiOperation(value = "修改用户详细信息")
    public ResultBean<?> updateUser(SysUserDetailReq sysUserDetailReq) {
        return ResultBean.error("功能未实现");
    }

    @PostMapping("/create-detail")
    @ApiOperation(value = "创建用户详情信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "token", required = true)})
    public ResultBean<?> createUserDetail(SysUserDetailReq sysUserDetailReq) {
        AuthToken authToken = getAuthToken();
        if (authToken == null) {
            return ResultBean.error("用户似乎并没有认证！");
        }
        sysUserService.creatUserDetails(authToken.getUserId(), sysUserDetailReq);
        return ResultBean.success("新增用户详情信息成功！");
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
    @ApiOperation(value = "获取用户精简信息列表", consumes = "只包含被开启的用户，主要用于前端的下拉选项")
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
