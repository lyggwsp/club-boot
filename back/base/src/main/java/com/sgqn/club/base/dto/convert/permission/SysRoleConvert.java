package com.sgqn.club.base.dto.convert.permission;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgqn.club.base.constant.CommonStatusEnum;
import com.sgqn.club.base.constant.SysRoleTypeEnum;
import com.sgqn.club.base.dto.req.permission.role.SysRoleReq;
import com.sgqn.club.base.dto.resp.permission.SysRoleExcelResp;
import com.sgqn.club.base.dto.resp.permission.SysRoleResp;
import com.sgqn.club.base.entity.SysRole;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


/**
 * @author wspstart
 * @description
 * @create 2023-06-13 22:57
 */
public class SysRoleConvert {


    public static SysRole req2do(SysRoleReq sysRoleReq) {
        return BeanUtil.copyProperties(sysRoleReq, SysRole.class);
    }

    public static IPage<SysRoleResp> do2resp(IPage<SysRole> sysRolePage) {
        return sysRolePage.convert(SysRoleConvert::do2resp);
    }

    public static SysRoleResp do2resp(SysRole sysRole) {
        return BeanUtil.copyProperties(sysRole, SysRoleResp.class);
    }

    public static List<SysRoleExcelResp> do2resp(List<SysRole> sysRoleList) {
        List<SysRoleExcelResp> res = new ArrayList<>();
        sysRoleList.forEach(role -> {
            SysRoleExcelResp excelRole = SysRoleExcelResp.builder().id(role.getId().toString())
                    .name(role.getName())
                    .description(role.getDescription())
                    .code(role.getCode())
                    .sort(role.getSort())
                    .status(role.getStatus().equals(CommonStatusEnum.DISABLE.getType())
                            ? CommonStatusEnum.DISABLE.getName() : CommonStatusEnum.ENABLE.getName())
                    .type(role.getType().equals(SysRoleTypeEnum.SYSTEM.getType())
                            ? SysRoleTypeEnum.SYSTEM.getName() : SysRoleTypeEnum.CUSTOM.getName())
                    .createTime(role.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .build();
            res.add(excelRole);
        });
        return res;
    }


    public static <T, K> Map<K, T> do2Map(List<T> list, Function<T, K> keyExtractor) {
        HashMap<K, T> map = new HashMap<>();
        list.forEach(item -> map.put(keyExtractor.apply(item), item));
        return map;
    }
}
