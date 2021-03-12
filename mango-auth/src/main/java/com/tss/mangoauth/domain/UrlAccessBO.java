package com.tss.mangoauth.domain;

import com.tss.mangoauth.entity.RoleEntity;
import com.tss.mangoauth.service.RoleService;

import java.util.List;

/**
 * @author YC01224 yangxiangjun
 * @description
 * @date 2021/3/8 17:19
 * @since JDK 1.8
 */
public class UrlAccessBO {
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

    private List<RoleEntity> roles;

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

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UrlAccessBO{" +
                "urlId='" + urlId + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", route='" + route + '\'' +
                ", urlName='" + urlName + '\'' +
                ", description='" + description + '\'' +
                ", roles=" + roles +
                '}';
    }
}
