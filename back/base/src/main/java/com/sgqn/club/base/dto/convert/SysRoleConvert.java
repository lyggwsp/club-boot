package com.sgqn.club.base.dto.convert;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgqn.club.base.dto.resp.SysRoleResp;
import com.sgqn.club.base.entity.SysRole;


/**
 * @author wspstart
 * @description
 * @create 2023-06-13 22:57
 */
public class SysRoleConvert {


    public static IPage<SysRoleResp> do2resp(IPage<SysRole> sysRolePage) {
        return sysRolePage.convert(SysRoleConvert::do2resp);
    }

    public static SysRoleResp do2resp(SysRole sysRole) {
        return BeanUtil.copyProperties(sysRole, SysRoleResp.class);
    }

}
