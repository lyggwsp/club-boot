package com.sgqn.club.base.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wspstart
 * @description 公共状态值枚举类
 * @create 2023-06-15 15:52
 */
@Getter
@AllArgsConstructor
public enum CommonStatusEnum {

    /**
     * 启用状态
     */
    ENABLE(0, "启用"),

    /**
     * 禁用状态
     */
    DISABLE(1, "禁用");

    private final Integer type;

    private final String name;


}
