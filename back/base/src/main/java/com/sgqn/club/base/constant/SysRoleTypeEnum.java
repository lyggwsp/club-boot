package com.sgqn.club.base.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wspstart
 * @description 系统角色枚举类型
 * @create 2023-06-15 16:01
 */
@Getter
@AllArgsConstructor
public enum SysRoleTypeEnum {

    /**
     * 内置角色类型
     */
    SYSTEM(0, "内置"),

    /**
     * 自定义角色类型
     */
    CUSTOM(1, "自定义");
    private final Integer type;

    private final String name;
}
