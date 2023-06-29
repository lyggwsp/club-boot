package com.sgqn.club.security;

import com.sgqn.club.base.entity.AuthToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * @author wspstart
 * @description springSecurity工具方便快速获取信息
 * @create 2023-06-29 21:11
 */
public class SecurityFrameworkUtils {

    /**
     * 获得当前认证信息
     *
     * @return 认证信息
     */
    public static Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }
        return context.getAuthentication();
    }

    /**
     * 获取当前认证的authToken信息，其中包含用户ID，角色ID，社团ID
     *
     * @return 查找到返回authToken，若没有查找到返回null
     */
    public static AuthToken getAuthToken() {
        Object details = Objects.requireNonNull(getAuthentication()).getPrincipal();
        if (details instanceof AuthToken) {
            return (AuthToken) details;
        }
        return null;
    }


    /**
     * 获取当前登录认证的用户ID
     *
     * @return 返回登录的用户ID, 无返回Null
     */
    public static Long getLoginUserId() {
        AuthToken authToken = getAuthToken();
        if (Objects.nonNull(authToken)) {
            return authToken.getUserId();
        }
        return null;
    }


}
