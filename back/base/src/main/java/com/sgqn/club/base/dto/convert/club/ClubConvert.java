package com.sgqn.club.base.dto.convert.club;

import cn.hutool.core.bean.BeanUtil;
import com.sgqn.club.base.dto.req.club.ClubReq;
import com.sgqn.club.base.entity.Club;

/**
 * @author wspstart
 * @description
 * @create 2023-06-30 21:26
 */
public class ClubConvert {

    public static Club req2do(ClubReq clubReq) {
        return BeanUtil.copyProperties(clubReq, Club.class);
    }
}
