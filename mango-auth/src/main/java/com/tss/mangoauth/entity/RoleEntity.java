package com.tss.mangoauth.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
/**
 * <p>
 * 角色表
 * </p>
 *
 * @author 
 * @since 2021-03-08
 */
@TableName("sys_role")
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 角色标识
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色状态
     */
    private String roleStatus;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
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
        return "Role{" +
        ", roleId=" + roleId +
        ", roleCode=" + roleCode +
        ", roleName=" + roleName +
        ", roleStatus=" + roleStatus +
        ", description=" + description +
        ", createTime=" + createTime +
        "}";
    }
}
