package com.tss.mangosercivea.config;

/**
 * @author YC01224 yangxiangjun
 * @description
 * @date 2021/3/5 14:54
 * @since JDK 1.8
 */
import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DataSourceConfig {

    private static final String DATASOURCE_NAME = "dbDataSource";

    /**
     * 数据源配置的前缀，必须与application.properties中配置的对应数据源的前缀一致
     */
    private static final String BUSINESS_DATASOURCE_PREFIX = "spring.datasource.druid.business";

    private static final String QUARTZ_DATASOURCE_PREFIX = "spring.datasource.druid.quartz";

    @Primary
    @Bean(name = DATASOURCE_NAME)
    @ConfigurationProperties(prefix = BUSINESS_DATASOURCE_PREFIX)
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * @QuartzDataSource 注解则是配置Quartz独立数据源的配置
     */
    @Bean
    @QuartzDataSource
    @ConfigurationProperties(prefix = QUARTZ_DATASOURCE_PREFIX)
    public DataSource quartzDataSource(){
        return new DruidDataSource();
    }
}
