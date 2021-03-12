package com.tss.mangoactiviti.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author YC01224 yangxiangjun
 * @description
 * @date 2021/3/11 11:21
 * @since JDK 1.8
 */
@EnableWebSecurity
public class ActivitiConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .anyRequest()
                .permitAll();
    }
}
