package com.sgqn.club.base.dto.convert.permission;

import cn.hutool.core.bean.BeanUtil;
import com.sgqn.club.base.dto.req.permission.permission.PermissionAssignUserClubRoleReq;
import com.sgqn.club.base.dto.req.user.SysUserReq;
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

    /**
     * 将请求用户转换为用户社团角色实体
     * @param sysUserReq  请求实体
     * @return 转换后的社团角色实体
     */
    public static SysUserRoleClub req2do(SysUserReq sysUserReq){
        return BeanUtil.copyProperties(sysUserReq,SysUserRoleClub.class);
    }
}
