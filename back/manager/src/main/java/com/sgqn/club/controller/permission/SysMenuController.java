package com.sgqn.club.controller.permission;

import com.sgqn.club.base.bean.ResultBean;
import com.sgqn.club.base.dto.convert.permission.SysMenuConvert;
import com.sgqn.club.base.dto.req.permission.menu.SysMenuCreateReq;
import com.sgqn.club.base.dto.req.permission.menu.SysMenuUpdateReq;
import com.sgqn.club.base.entity.SysMenu;
import com.sgqn.club.base.service.SysMenuService;
import com.sgqn.club.base.validation.ValidGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
@RestController
@RequestMapping("/sys-menu")
@Api(tags = "系统菜单控制器[sys-menu]")
public class SysMenuController {


    @Autowired
    private SysMenuService sysMenuService;


    @PostMapping("/create")
    @ApiOperation("创建菜单[createMenu]")
    public ResultBean<?> createMenu(@RequestBody @Validated({ValidGroup.Insert.class}) SysMenuCreateReq sysMenuCreateReq) {
        SysMenu sysMenu = SysMenuConvert.req2do(sysMenuCreateReq);
        Long menuId = sysMenuService.createMenu(sysMenu);
        return menuId > 0 ? ResultBean.success("创建成功")
                : ResultBean.error("创建菜单失败");
    }

    @PutMapping("/update")
    @ApiOperation("修改菜单[updateMenu]")
    public ResultBean<?> updateMenu(SysMenuUpdateReq sysMenuUpdateReq) {
        SysMenu sysMenu = SysMenuConvert.req2do(sysMenuUpdateReq);
        sysMenuService.updateMenu(sysMenu);
        return ResultBean.success("更新菜单成功");
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除菜单[deleteMenu]")
    public ResultBean<?> deleteMenu() {
        return ResultBean.error("功能待实现");
    }

    @GetMapping("/list")
    @ApiOperation("获取菜单列表[getMenuList]")
    public ResultBean<?> getMenuList() {
        return ResultBean.error("功能待实现");
    }

    @GetMapping("/list-all-simple")
    @ApiOperation("获取菜单精简信息列表[getSimpleMenuList]")
    public ResultBean<?> getSimpleMenuList() {
        return ResultBean.error("功能待实现");
    }

    @GetMapping("/get")
    @ApiOperation("根据ID获取菜单信息")
    public ResultBean<?> getMenu() {
        return ResultBean.error("功能待实现");
    }

}
