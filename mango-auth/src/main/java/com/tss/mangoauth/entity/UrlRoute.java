package com.tss.mangoauth.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
/**
 * <p>
 * API接口表
 * </p>
 *
 * @author 
 * @since 2021-03-08
 */
@TableName("sys_url_route")
public class UrlRoute implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口ID
     */
    private String urlId;

    /**
     * 服务ID
     */
    private String serviceId;

    /**
     * 接口路径
     */
    private String route;

    /**
     * 接口名称
     */
    private String urlName;

    /**
     * 服务描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
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
        return "UrlRoute{" +
        ", urlId=" + urlId +
        ", serviceId=" + serviceId +
        ", route=" + route +
        ", urlName=" + urlName +
        ", description=" + description +
        ", createTime=" + createTime +
        "}";
    }
}
