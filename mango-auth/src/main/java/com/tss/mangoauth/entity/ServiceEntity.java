package com.tss.mangoauth.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
/**
 * <p>
 * 服务表
 * </p>
 *
 * @author 
 * @since 2021-03-08
 */
@TableName("sys_service")
public class ServiceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务ID
     */
    private String serviceId;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务占用端口
     */
    private String port;

    /**
     * 服务描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Service{" +
        ", serviceId=" + serviceId +
        ", serviceName=" + serviceName +
        ", port=" + port +
        ", description=" + description +
        ", createTime=" + createTime +
        "}";
    }
}
