package com.sgqn.club.controller.permission;

import com.sgqn.club.base.bean.ResultBean;
import com.sgqn.club.base.constant.CommonStatusEnum;
import com.sgqn.club.base.dto.condition.SysMenuCondition;
import com.sgqn.club.base.dto.convert.permission.SysMenuConvert;
import com.sgqn.club.base.dto.req.permission.menu.SysMenuCreateReq;
import com.sgqn.club.base.dto.req.permission.menu.SysMenuUpdateReq;
import com.sgqn.club.base.dto.resp.permission.SysMenuResp;
import com.sgqn.club.base.entity.SysMenu;
import com.sgqn.club.base.service.permisson.SysMenuService;
import com.sgqn.club.base.validation.ValidGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.Comparator;
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
@RequestMapping("/sys-menu")
@Api(tags = "管理后台 - 菜单[sys-menu]")
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
    public ResultBean<?> updateMenu(@RequestBody SysMenuUpdateReq sysMenuUpdateReq) {
        SysMenu sysMenu = SysMenuConvert.req2do(sysMenuUpdateReq);
        sysMenuService.updateMenu(sysMenu);
        return ResultBean.success("更新菜单成功");
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除菜单[deleteMenu]")
    public ResultBean<?> deleteMenu(@RequestHeader("X-Menu-Id") Long id) {
        sysMenuService.deleteMenu(id);
        return ResultBean.success("删除菜单成功");
    }

    @GetMapping("/list")
    @ApiOperation("获取菜单列表[getMenuList]")
    public ResultBean<?> getMenuList(SysMenuCondition sysMenuCondition) {
        List<SysMenu> menuList = sysMenuService.getMenuList(sysMenuCondition);
        if (!menuList.isEmpty()) {
            List<SysMenuResp> sysMenuResps = SysMenuConvert.do2resp02(menuList);
            sysMenuResps.sort(Comparator.comparing(SysMenuResp::getSort));
            return ResultBean.success("获取信息成功",sysMenuResps);
        }
        return ResultBean.error("暂无记录");
    }

    @GetMapping("/list-all-simple")
    @ApiOperation("获取菜单精简信息列表[getSimpleMenuList]")
    @PermitAll
    public ResultBean<?> getSimpleMenuList() {
        // 获取菜单列表，只要求开启状态
        SysMenuCondition menuCondition = SysMenuCondition.builder()
                .status(CommonStatusEnum.ENABLE.getType()).build();
        List<SysMenu> menuList = sysMenuService.getMenuList(menuCondition);
        if (!menuList.isEmpty()) {
            menuList.sort(Comparator.comparing(SysMenu::getSort));
            return ResultBean.success("获取信息成功",
                    SysMenuConvert.do2resp(menuList));
        }
        return ResultBean.error("暂无记录");
    }

    @GetMapping("/get")
    @ApiOperation("根据ID获取菜单信息")
    public ResultBean<?> getMenu(Long id) {
        SysMenu menu = sysMenuService.getMenu(id);
        return ResultBean.success("获取信息成功", menu);
    }

}
