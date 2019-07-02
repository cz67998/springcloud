package com.wangzhou.gateway.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * 跨域配置
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/23
 * Time:17:41
 * c - cross    0-origin  r-resource  s-sharing
 **/
//Configuration时在不同@Bean容器之中的是同一个对象，而使用Component时是不同的对象。
@Configuration
public class CorsConfig {
    @Bean
    public UrlBasedCorsConfigurationSource corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");//http://www.a.com
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        //在这个时间段里，相同的跨域请求就不在检查了  300s
        config.setMaxAge(300l);
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
