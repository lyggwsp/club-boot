package com.sgqn.club.base.dto.convert.permission;

import cn.hutool.core.bean.BeanUtil;
import com.sgqn.club.base.dto.req.permission.permission.PermissionAssignUserClubRoleReq;
import com.sgqn.club.base.entity.SysUserRoleClub;

/**
 * @author wspstart
 * @description
 * @create 2023-06-18 18:55
 */
public class PermissionConvert {

    public static SysUserRoleClub req2do(PermissionAssignUserClubRoleReq req){
        return BeanUtil.copyProperties(req,SysUserRoleClub.class);
    }
}
