package com.tss.mangoauth.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
/**
 * <p>
 * 接口权限表
 * </p>
 *
 * @author 
 * @since 2021-03-08
 */
@TableName("sys_url_access")
public class UrlAccess implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 权限ID
     */
    private String accessId;

    /**
     * 接口ID
     */
    private String urlId;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
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
        return "UrlAccess{" +
        ", id=" + id +
        ", accessId=" + accessId +
        ", urlId=" + urlId +
        ", description=" + description +
        ", createTime=" + createTime +
        "}";
    }
}
