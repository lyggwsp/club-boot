package com.sgqn.club.base.dto.convert.permission;

import cn.hutool.core.bean.BeanUtil;
import com.sgqn.club.base.dto.req.permission.menu.SysMenuCreateReq;
import com.sgqn.club.base.dto.req.permission.menu.SysMenuUpdateReq;
import com.sgqn.club.base.dto.resp.permission.SysMenuResp;
import com.sgqn.club.base.dto.resp.permission.SysMenuSimpleResp;
import com.sgqn.club.base.entity.SysMenu;

import java.util.List;

/**
 * @author wspstart
 * @description
 * @create 2023-06-18 9:44
 */
public class SysMenuConvert {

    public static SysMenu req2do(SysMenuCreateReq sysMenuCreateReq) {
        return BeanUtil.copyProperties(sysMenuCreateReq, SysMenu.class);
    }

    public static SysMenu req2do(SysMenuUpdateReq sysMenuUpdateReq) {
        return BeanUtil.copyProperties(sysMenuUpdateReq, SysMenu.class);
    }

    public static SysMenuResp do2resp(SysMenu sysMenu) {
        return BeanUtil.copyProperties(sysMenu, SysMenuResp.class);
    }


    public static List<SysMenuSimpleResp> do2resp(List<SysMenu> menuList) {
        return BeanUtil.copyToList(menuList, SysMenuSimpleResp.class);
    }

    public static List<SysMenuResp> do2resp02(List<SysMenu> menuList) {
        return BeanUtil.copyToList(menuList, SysMenuResp.class);
    }
}
