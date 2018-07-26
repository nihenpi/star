package com.skaz.star.config;

import com.skaz.cache.CacheTemplate;
import com.skaz.security.core.SecurityFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import com.skaz.security.core.SecurityManager;

/**
 * @Configuration  表明这是一个配置类
 * @EnableWebSecurity 开启web安全
 * @EnableGlobalMethodSecurity 开启方法安全控制
 * @ConditionalOnProperty 当指定属性存在且和havingValue的值相同时，这个配置类才生效
 * @author jungle
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConditionalOnProperty(name = "app.security", havingValue = "true")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CacheTemplate cacheTemplate;
    private final CorsFilter corsFilter;


    @Bean
    public SecurityManager securityManager() {
        return new SecurityManager(cacheTemplate);
    }

    public SecurityConfig(CorsFilter corsFilter, CacheTemplate cacheTemplate) {
        this.cacheTemplate = cacheTemplate;
        this.corsFilter = corsFilter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭跨域防御
        http.csrf().disable();
        // 禁用session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 禁用cache
        http.headers().cacheControl();
        // 解决不允许在iframe显示的问题
        http.headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable();
        // 将SecurityFilter添加在密码验证前面
        http.addFilterBefore(new SecurityFilter(securityManager()), UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated();


    }

}
