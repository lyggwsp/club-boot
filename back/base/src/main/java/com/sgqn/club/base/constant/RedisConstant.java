package com.sgqn.club.base.constant;

/**
 * @author wspstart
 * @description
 * @create 2023-06-23 0:52
 */
public class RedisConstant {

    public static String TOKEN_STORE = "club:auth:";
    /**
     * token过期时间
     */
    public static long TOKEN_EXP = 60 * 60 * 24;
}
