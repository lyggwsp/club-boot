package com.sgqn.club.base.dto.convert.permission;

import cn.hutool.core.bean.BeanUtil;
import com.sgqn.club.base.dto.req.permission.menu.SysMenuCreateReq;
import com.sgqn.club.base.dto.req.permission.menu.SysMenuUpdateReq;
import com.sgqn.club.base.dto.resp.permission.SysMenuResp;
import com.sgqn.club.base.dto.resp.permission.SysMenuSimpleResp;
import com.sgqn.club.base.entity.SysMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * do2树状结构
     * 通过id与parentId关联
     *
     * @param menuList 待转换的集合
     * @return 树状结构
     */
    public static List<SysMenuResp> do2resp03(List<SysMenu> menuList) {
        Map<Long, SysMenuResp> menuMap = new HashMap<>();
        List<SysMenuResp> menuRespList = new ArrayList<>();
        // 1、创建 SysMenuResp 对象并将其放入 map 中，以便通过 id 进行快速查找
        for (SysMenu menu : menuList) {
            SysMenuResp sysMenuResp = BeanUtil.copyProperties(menu, SysMenuResp.class);
            menuMap.put(menu.getId(), sysMenuResp);
        }
        // 2、构建菜单树结构
        for (SysMenuResp menuResp : menuMap.values()) {
            String parentId = menuResp.getParentId();
            if (parentId != null) {
                SysMenuResp parent = menuMap.get(Long.valueOf(parentId));
                if (parent != null) {
                    parent.getChildren().add(menuResp);
                }
            } else {
                menuRespList.add(menuResp);
            }
        }
        return menuRespList;
    }


}
