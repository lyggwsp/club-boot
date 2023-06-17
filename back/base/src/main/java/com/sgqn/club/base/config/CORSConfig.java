package com.sgqn.club.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author wspstart
 * @description 跨域问题解决
 * @create 2023-06-14 20:43
 */
//@Configuration
@Configuration
public class CORSConfig {


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.addAllowedOrigin("http://127.0.0.1:5173/");
//        corsConfiguration.addAllowedOrigin("http://localhost:5173");
//        corsConfiguration.addAllowedHeader("Access-Control-Allow-Origin");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("OPTIONS");
        corsConfiguration.addAllowedMethod("HEAD");
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("PUT");
        corsConfiguration.addAllowedMethod("PATCH");
        corsConfiguration.addAllowedMethod("DELETE");
        corsConfiguration.addExposedHeader("Authorization");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
