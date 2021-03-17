package com.tss.mangocommon.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author YC01224 yangxiangjun
 * @description 用于将请求中的用户信息添加到上下文（导入web组件使用）
 * @date 2021/3/12 10:32
 * @since JDK 1.8
 */
public class TransmitUserInfoFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(TransmitUserInfoFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String userId = servletRequest.getHeader(UserInfoContext.USER_ID);
        String userCode = servletRequest.getHeader(UserInfoContext.USER_CODE);
        String userName = servletRequest.getHeader(UserInfoContext.USER_NAME);
        String status = servletRequest.getHeader(UserInfoContext.STATUS);
        UserInfo userInfo = null;
        try {
            userInfo = new UserInfo(userId, userCode, userName, Integer.valueOf(status));
        } catch (NumberFormatException e) {
            logger.error("用户状态只能以数字形式表示");
        }
        UserInfoContext.setUserInfoContext(userInfo);
        chain.doFilter(request,response);
    }
}
