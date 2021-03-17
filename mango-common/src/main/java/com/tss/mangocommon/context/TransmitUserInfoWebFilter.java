//package com.tss.mangocommon.usercontext;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
///**
// * @author YC01224 yangxiangjun
// * @description 解析请求中的用户信息，并将用户信息添加到ThreadLocal变量
// * 需要添加@Configuration注解，且在引入公共模块的项目中，务必在启动类加@ComponentScan，这样才能在启动时被扫描加载
// * @date 2021/3/12 18:15
// * @since JDK 1.8
// */
//@Configuration
//public class TransmitUserInfoWebFilter implements WebFilter {
//    private static final Logger logger = LoggerFactory.getLogger(TransmitUserInfoWebFilter.class);
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        String userId = request.getHeaders().getFirst(UserInfoContext.USER_ID);
//        String userCode = request.getHeaders().getFirst(UserInfoContext.USER_CODE);
//        String userName = request.getHeaders().getFirst(UserInfoContext.USER_NAME);
//        String status = request.getHeaders().getFirst(UserInfoContext.STATUS);
//        UserInfo userInfo = null;
//        try {
//            userInfo = new UserInfo(userId, userCode, userName, Integer.valueOf(status));
//        } catch (NumberFormatException e) {
//            logger.error("用户状态只能以数字形式表示");
//        }
//        UserInfoContext.setUserInfoContext(userInfo);
//        return chain.filter(exchange);
//    }
//}
