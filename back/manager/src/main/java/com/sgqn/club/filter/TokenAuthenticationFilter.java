package com.sgqn.club.filter;

import cn.hutool.core.convert.Convert;
import com.sgqn.club.base.entity.AuthToken;
import com.sgqn.club.base.service.auth.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.sgqn.club.base.constant.RedisConstant.TOKEN_STORE;

/**
 * @author wspstart
 * @description
 * @create 2023-06-21 23:36
 */
@Slf4j
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    private StringRedisTemplate stringRedisTemplate;

    private TokenService tokenService;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, StringRedisTemplate stringRedisTemplate,
                                     TokenService tokenService) {
        super(authenticationManager);
        this.tokenService = tokenService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            log.info("请求走TokenAuthenticationFilter过滤器,获取到token{}", token);
            // 1、 解析token
            AuthToken authToken = tokenService.parseToken(token);
            // 2、根据用户ID获取对应缓存在redis的权限角色信息
            String permissionStr = stringRedisTemplate.opsForValue().get(TOKEN_STORE + authToken.getUserId());
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            if (!StringUtils.isEmpty(permissionStr)) {
                String[] permissions = permissionStr.split(",");
                List<String> permissionLists = (List<String>) Convert.toList(permissions);
                // 构造SimpleGrantedAuthority
                permissionLists.forEach(i -> {
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(i);
                    authorities.add(authority);
                });
            }
            // 3、设置认证信息
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authToken, token, authorities);
            // 5、将UsernamePasswordAuthenticationToken设置到springSecurity应用上下文中
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
