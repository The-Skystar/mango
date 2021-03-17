package com.tss.mangogateway.filter;

import org.springframework.core.NamedThreadLocal;

import java.util.Objects;

/**
 * @author YC01224 yangxiangjun
 * @description 用户信息上下文
 * @date 2021/3/12 10:21
 * @since JDK 1.8
 */
public class UserInfoContext {
    public static final String USER_ID = "USER_ID";
    public static final String USER_CODE = "USER_CODE";
    public static final String USER_NAME = "USER_NAME";
    public static final String STATUS = "STATUS";
    public static final String AUTHORITIES = "AUTHORITIES";

    private static final ThreadLocal<UserInfo> USER_INFO_CONTEXT = new NamedThreadLocal<>("user info context");

    public static UserInfo getUserInfoContext(){
        UserInfo userInfo = UserInfoContext.USER_INFO_CONTEXT.get();
        if (Objects.isNull(userInfo)) {
            userInfo = new UserInfo();
            USER_INFO_CONTEXT.set(userInfo);
        }
        return userInfo;
    }

    public static void setUserInfoContext(UserInfo userInfo){
        USER_INFO_CONTEXT.set(userInfo);
    }
}
