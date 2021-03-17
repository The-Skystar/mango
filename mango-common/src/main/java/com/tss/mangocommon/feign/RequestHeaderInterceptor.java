package com.tss.mangocommon.feign;

import com.tss.mangocommon.context.UserInfo;
import com.tss.mangocommon.context.UserInfoContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Objects;

/**
 * @author YC01224 yangxiangjun
 * @description 使用feign进行服务间调用时，首先将上下文的用户信息加到header里面，这下被调用的服务才能获取到用户信息
 * @date 2021/3/12 18:22
 * @since JDK 1.8
 */
public class RequestHeaderInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        UserInfo userInfoContext = UserInfoContext.getUserInfoContext();
        if (Objects.isNull(userInfoContext)) {
            return;
        }
        requestTemplate.header(UserInfoContext.USER_ID,userInfoContext.getUserId());
        requestTemplate.header(UserInfoContext.USER_NAME,userInfoContext.getUsername());
        requestTemplate.header(UserInfoContext.USER_CODE,userInfoContext.getUserCode());
        requestTemplate.header(UserInfoContext.STATUS,userInfoContext.getStatus() == null ? null : userInfoContext.getStatus().toString());
    }
}
