package com.tss.mangogateway.filter;

/**
 * @author YC01224 yangxiangjun
 * @description
 * @date 2021/3/12 10:15
 * @since JDK 1.8
 */
public class UserInfo {
    private String userId;
    private String userCode;
    private String username;
    private Integer status;

    public UserInfo() {
    }

    public UserInfo(String userId, String userCode, String username, Integer status) {
        this.userId = userId;
        this.userCode = userCode;
        this.username = username;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + userId + '\'' +
                ", userCode='" + userCode + '\'' +
                ", username='" + username + '\'' +
                ", status=" + status +
                '}';
    }
}
