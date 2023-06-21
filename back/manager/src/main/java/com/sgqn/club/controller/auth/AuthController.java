package com.sgqn.club.controller.auth;

import com.sgqn.club.base.bean.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wspstart
 * @description
 * @create 2023-06-20 20:18
 */
@RestController
@RequestMapping("/system/auth")
@Api(tags = "管理后台 - 认证[user-auth]")
public class AuthController {

    @PostMapping("/login")
    @ApiOperation("使用账号密码登录")
    public ResultBean<?> login() {
        return ResultBean.error("功能未实现");
    }

    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public ResultBean<?> logout() {
        return ResultBean.error("功能未实现");
    }

    @GetMapping("/get-permission-info")
    @ApiOperation("获取登录用户的权限信息")
    public ResultBean<?> getPermissionInfo() {
        return ResultBean.error("功能未实现");
    }


}
