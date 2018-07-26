package com.skaz.star.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

/**
 * 业务系统配置
 * @author jungle
 */
@Getter
@Configuration
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {

    private final CorsConfiguration cors = new CorsConfiguration();

}
