package com.sgqn.club.security;

import com.sgqn.club.base.bean.ResultBean;
import com.sgqn.club.base.utils.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wspstart
 * @description
 * @create 2023-06-21 19:56
 */
public class UnAuthEntryPoint implements AuthenticationEntryPoint {

    /**
     * 权限认证失败执行的方法
     *
     * @param request       请求
     * @param response      响应
     * @param authException 异常对象
     * @throws IOException      io
     * @throws ServletException 异常
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 没有权限会往response中存放一些数据 状态码，信息，数据
        ServletUtils.writeJson(response, ResultBean.error("无权访问"));
    }
}
