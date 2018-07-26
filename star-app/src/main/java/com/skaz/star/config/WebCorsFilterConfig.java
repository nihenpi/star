package com.skaz.star.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 *
 * @author jungle
 */
@Configuration
public class WebCorsFilterConfig {

    @Bean
    public CorsFilter corsFilter(ApplicationProperties applicationProperties) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = applicationProperties.getCors();
        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
            config.addAllowedOrigin("*");
            config.addAllowedHeader("*");
            config.addAllowedMethod("*");
            config.setMaxAge(36000L);
            source.registerCorsConfiguration("/**", config);
        }
        return new CorsFilter(source);
    }

}
