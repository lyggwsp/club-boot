package com.sgqn.club.base.service.auth;

import com.sgqn.club.base.entity.AuthToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wspstart
 * @description
 * @create 2023-06-22 9:26
 */
@Service
public class TokenServiceImpl implements TokenService {


    @Value("${token.security-key}")
    private String securityKey;

    @Value("${token.expires-time}")
    private Long expiresTime;

    @Override
    public AuthToken createAccessToken(Long userId, Long roleId,Long clubId) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("clubId", clubId);
        claims.put("roleId", roleId);

        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiresTime))
                .signWith(SignatureAlgorithm.HS256, securityKey)
                .compact();
        return AuthToken.builder().userId(userId).accessToken(token)
                .expiresTime(new Date(System.currentTimeMillis() + expiresTime)).build();
    }


    @Override
    public AuthToken parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(securityKey)
                .parseClaimsJws(token)
                .getBody();

        Long userId = claims.get("userId", Long.class);
        Long clubId = claims.get("clubId", Long.class);
        Long roleId = claims.get("roleId", Long.class);

        return AuthToken.builder().userId(userId)
                .clubId(clubId).roleId(roleId).build();
    }
}
