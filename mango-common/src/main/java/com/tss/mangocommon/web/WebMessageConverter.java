package com.tss.mangocommon.web;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.util.TypeUtils;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.StandardCharsets;

/**
 * @author YC01224 yangxiangjun
 * @description
 * @date 2021/3/15 10:50
 * @since JDK 1.8
 */
@Configuration
public class WebMessageConverter{

    @Bean
    public HttpMessageConverters httpMessageConverters(){
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        TypeUtils.compatibleWithJavaBean = true;

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,SerializerFeature.WriteMapNullValue);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");

        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        return new HttpMessageConverters(fastJsonHttpMessageConverter);
    }
}
