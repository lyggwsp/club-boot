package com.sgqn.club.base.service.auth;

import com.sgqn.club.base.dto.req.auth.AuthLoginReq;
import com.sgqn.club.base.dto.resp.auth.AuthLoginResp;
import com.sgqn.club.base.entity.AuthToken;
import com.sgqn.club.base.entity.SysUser;

/**
 * @author wspstart
 * @description
 * @create 2023-06-22 0:16
 */
public interface AuthService {

    /**
     * 用户登录
     *
     * @param authLoginReq 登录请求实体
     * @return 登录响应实体
     */
    AuthLoginResp login(AuthLoginReq authLoginReq);


    /**
     * 验证账号 + 密码。如果通过，则返回用户
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 用户
     */
    SysUser authenticate(String username, String password, Long roleId, Long clubId);


    /**
     * 用户退出登录
     *
     * @param authToken 用户
     */
    void logout(AuthToken authToken);
}
