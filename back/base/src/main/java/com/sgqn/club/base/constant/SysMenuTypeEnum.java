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
public enum SysMenuTypeEnum {

    DIR(1), // 目录
    MENU(2), // 菜单
    BUTTON(3) // 按钮
    ;

    /**
     * 类型
     */
    private final Integer type;

}