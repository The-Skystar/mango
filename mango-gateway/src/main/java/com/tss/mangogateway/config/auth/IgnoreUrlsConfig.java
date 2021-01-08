package com.tss.mangogateway.config.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 网关白名单配置
 * Created by yangxiangjun on 2020/6/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix="secure.ignore")
@RefreshScope
public class IgnoreUrlsConfig {
    private String[] urls;
}
