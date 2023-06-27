package com.sgqn.club.controller.auth;

import com.sgqn.club.base.bean.ResultBean;
import com.sgqn.club.base.dto.req.auth.AuthLoginReq;
import com.sgqn.club.base.service.auth.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

/**
 * @author wspstart
 * @description
 * @create 2023-06-20 20:18
 */
@RestController
@RequestMapping("/system/auth")
@Api(tags = "管理后台 - 认证[user-auth]")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ApiOperation("使用账号密码登录")
    @PermitAll
    public ResultBean<?> login(AuthLoginReq authLoginReq) {
        return ResultBean.success("登录成功",
                authService.login(authLoginReq));
    }

    @PostMapping("/logout")
    @ApiOperation("退出登录")
    @PreAuthorize("hasRole('admin')")
    public ResultBean<?> logout() {
        return ResultBean.error("功能未实现");
    }

    @GetMapping("/get-permission-info")
    @ApiOperation("获取登录用户的权限信息")
    public ResultBean<?> getPermissionInfo() {
        return ResultBean.error("功能未实现");
    }


    @GetMapping("/get-club-role/{username}")
    @ApiOperation("获取登录用户的权限信息")
    public ResultBean<?> getClubRoleByUserName(@PathVariable("username") String username) {
        return ResultBean.error("功能未实现");
    }

}
