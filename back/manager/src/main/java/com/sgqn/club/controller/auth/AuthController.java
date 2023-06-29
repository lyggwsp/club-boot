package com.sgqn.club.controller.auth;

import cn.hutool.core.convert.Convert;
import com.sgqn.club.base.bean.ResultBean;
import com.sgqn.club.base.constant.CommonStatusEnum;
import com.sgqn.club.base.constant.SysMenuTypeEnum;
import com.sgqn.club.base.dto.convert.permission.SysMenuConvert;
import com.sgqn.club.base.dto.req.auth.AuthLoginReq;
import com.sgqn.club.base.dto.resp.permission.SysMenuResp;
import com.sgqn.club.base.entity.AuthToken;
import com.sgqn.club.base.entity.SysMenu;
import com.sgqn.club.base.service.auth.AuthService;
import com.sgqn.club.base.service.permisson.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.Arrays;
import java.util.List;

import static com.sgqn.club.security.SecurityFrameworkUtils.getAuthToken;

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

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/login")
    @ApiOperation("使用账号密码登录")
    @PermitAll
    public ResultBean<?> login(AuthLoginReq authLoginReq) {
        return ResultBean.success("登录成功",
                authService.login(authLoginReq));
    }

    @PostMapping("/logout")
    @ApiOperation("退出登录")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "token", required = true)})
    @PermitAll
    public ResultBean<?> logout() {
        AuthToken authToken = getAuthToken();
        authService.logout(authToken);
        return ResultBean.success("退出成功！");
    }

    @GetMapping("/get-permission-info")
    @ApiOperation("获取登录用户的权限信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "token", required = true)})
    public ResultBean<?> getPermissionInfo() {
        // 1、获取认证信息
        AuthToken authToken = getAuthToken();
        if (authToken == null) {
            return ResultBean.error("用户似乎并没有认证");
        }
        // 2、根据角色ID获取角色对应的菜单信息
        List<SysMenu> roleMenuList = permissionService.getRoleMenuList(authToken.getRoleId(),
                Arrays.asList(SysMenuTypeEnum.DIR.getType(), SysMenuTypeEnum.MENU.getType()),
                Convert.toSet(Integer.class, CommonStatusEnum.ENABLE.getType()));

        List<SysMenuResp> sysMenuResps = SysMenuConvert.do2resp03(roleMenuList);

        return ResultBean.success("获取菜单成功！", sysMenuResps);
    }

}
