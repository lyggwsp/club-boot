package com.sgqn.club.base.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author wspstart
 * @description
 * @create 2023-06-22 9:30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 社团编号
     */
    private Long clubId;

    /**
     * 角色编号
     */
    private Long roleId;

    /**
     * 访问token
     */
    private String accessToken;

    /**
     * 过期时间
     */
    private Date expiresTime;
}
