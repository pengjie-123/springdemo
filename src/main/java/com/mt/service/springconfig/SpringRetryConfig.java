package com.mt.service.springconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry
public class SpringRetryConfig {
}
