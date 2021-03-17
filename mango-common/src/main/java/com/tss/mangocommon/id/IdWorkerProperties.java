package com.tss.mangocommon.id;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author YC01224 yangxiangjun
 * @description ID生成器的参数配置
 * @date 2021/3/17 10:26
 * @since JDK 1.8
 */
@ConfigurationProperties(prefix = "spring.worker")
public class IdWorkerProperties {

    private long datacenterId;
    private long machineId;

    public long getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(long datacenterId) {
        this.datacenterId = datacenterId;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }
}
