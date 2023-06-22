package com.sgqn.club.base.service.auth;

import com.sgqn.club.base.entity.AuthToken;

/**
 * @author wspstart
 * @description
 * @create 2023-06-22 9:26
 */
public interface TokenService {

    /**
     * 创建token
     * @param userId 用户ID
     * @param clubId 社团ID
     * @param roleId 角色ID
     * @return token
     */
    AuthToken createAccessToken(Long userId,Long clubId,Long roleId);

    /**
     * 解析token
     * @param token token
     * @return 解析后结果
     */
    AuthToken parseToken(String token);
}
