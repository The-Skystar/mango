package com.tss.mangoservicea.manager.template.core;

import freemarker.template.Configuration;
import freemarker.template.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * freemaker导出excel的配置类
 * Created by yangxiangjun on 2021/1/27.
 */
public class FreeMarkerConfig {
    private static Logger log = LoggerFactory.getLogger(FreeMarkerConfig.class);

    public static Configuration getInstance(){
        return ConfigEnum.CONFIG_INSTANCE.getInstance();
    }

    public enum ConfigEnum{
        /**
         *
         */
        CONFIG_INSTANCE;

        private Configuration instance;

        ConfigEnum() {
            // 设置FreeMarker的版本和编码格式
            Configuration configuration = new Configuration(new Version("2.3.28"));
            configuration.setDefaultEncoding("UTF-8");
            this.instance = configuration;
        }

        public Configuration getInstance() {
            return instance;
        }
    }
}
