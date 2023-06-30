package com.sgqn.club.base.dto.convert.user;

import cn.hutool.core.bean.BeanUtil;
import com.sgqn.club.base.dto.req.user.SysUserDetailReq;
import com.sgqn.club.base.dto.req.user.SysUserReq;
import com.sgqn.club.base.entity.SysUser;
import com.sgqn.club.base.entity.UserDetail;

/**
 * @author wspstart
 * @description
 * @create 2023-06-30 0:38
 */
public class UserConvert {

    public static SysUser req2do(SysUserReq sysUserReq) {
        return BeanUtil.copyProperties(sysUserReq, SysUser.class);
    }

    public static UserDetail req2do(SysUserDetailReq sysUserDetailReq) {
        return BeanUtil.copyProperties(sysUserDetailReq, UserDetail.class);
    }
}
