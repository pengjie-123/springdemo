package com.mt.service.springconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.retry.annotation.EnableRetry;

import javax.annotation.PostConstruct;

@Configuration
@EnableRetry
public class SpringRetryConfig {

    @Bean("retryPlaceHolderBean")
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer () {
        PropertyPlaceholderConfigurer holder =  new PropertyPlaceholderConfigurer();
        holder.setLocation(new ClassPathResource("spring-retry.properties"));
        return holder;
    }

}
