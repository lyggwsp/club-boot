package com.sgqn.club.base.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wspstart
 * @description
 * @create 2023-06-22 0:53
 */
@Getter
@AllArgsConstructor
public enum LoginResultEnum {

    SUCCESS(0), // 成功
    BAD_CREDENTIALS(10), // 账号或密码不正确
    USER_DISABLED(20), // 用户被禁用
    LOGOUT_SELF(200),  // 自己主动登出
    LOGOUT_DELETE(202), // 强制退出
    ;
    /**
     * 结果
     */
    private final Integer result;
}
