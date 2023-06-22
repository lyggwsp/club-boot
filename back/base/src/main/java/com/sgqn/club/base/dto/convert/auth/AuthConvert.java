package com.sgqn.club.base.dto.convert.auth;

import cn.hutool.core.bean.BeanUtil;
import com.sgqn.club.base.dto.resp.auth.AuthLoginResp;
import com.sgqn.club.base.entity.AuthToken;

/**
 * @author wspstart
 * @description
 * @create 2023-06-22 17:45
 */
public class AuthConvert {

    public static AuthLoginResp do2Resp(AuthToken authToken){
        return BeanUtil.copyProperties(authToken,AuthLoginResp.class);
    }
}
