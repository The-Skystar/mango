package com.tss.mangocommon.context;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author YC01224 yangxiangjun
 * @description
 * @date 2021/3/12 17:14
 * @since JDK 1.8
 */
@Configuration
public class WebFilterConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean registrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new TransmitUserInfoFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("TransmitUserInfoFilter");
        return registrationBean;
    }
}
