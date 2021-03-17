package com.tss.mangocommon.id;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.ConfigurationException;

/**
 * @author YC01224 yangxiangjun
 * @description
 * @date 2021/3/17 10:30
 * @since JDK 1.8
 */
@Configuration
@EnableConfigurationProperties(IdWorkerProperties.class)
public class IdWorkerConfig {

    @Bean
    public IdWorker idWorker(IdWorkerProperties properties) throws ConfigurationException {
        if (properties.getDatacenterId() == 0 || properties.getMachineId() == 0) {
            throw new ConfigurationException("参数spring.worker.datacenterId和spring.worker.machineId未正确配置。");
        }
        return new IdWorker(properties.getDatacenterId(), properties.getMachineId());
    }
}
