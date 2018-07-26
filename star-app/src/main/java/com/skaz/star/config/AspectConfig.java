package com.skaz.star.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author jungle
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class AspectConfig {}
