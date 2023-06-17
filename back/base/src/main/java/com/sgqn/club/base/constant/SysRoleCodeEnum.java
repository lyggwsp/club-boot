package com.sgqn.club.base.constant;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wspstart
 * @description
 * @create 2023-06-17 13:45
 */
@Getter
@AllArgsConstructor
public enum SysRoleCodeEnum {

    SUPER_ADMIN("super_admin", "超级管理员");

    /**
     * 角色编码
     */
    private final String code;
    /**
     * 名字
     */
    private final String name;

    /**
     * 校验是否是超级管理员
     *
     * @param code 角色编码
     * @return 返回是否是超级管理员的编码
     */
    public static boolean isSuperAdmin(String code) {
        return ObjectUtil.equal(code, SUPER_ADMIN.code);
    }

}
