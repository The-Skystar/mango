package com.tss.mangoauth.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
/**
 * <p>
 * 权限表
 * </p>
 *
 * @author 
 * @since 2021-03-08
 */
@TableName("sys_access")
public class AccessEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    private String accessId;

    /**
     * 权限名称
     */
    private String accessName;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
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
        return "Access{" +
        ", accessId=" + accessId +
        ", accessName=" + accessName +
        ", description=" + description +
        ", createTime=" + createTime +
        "}";
    }
}
