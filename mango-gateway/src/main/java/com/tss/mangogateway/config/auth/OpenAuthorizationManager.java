package com.tss.mangogateway.config.auth;

import com.tss.mangogateway.filter.AuthGlobalFilter;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 开放平台接口API鉴权管理器
 * Created by yangxiangjun on 2020/12/11.
 */
@Component
public class OpenAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private static Logger log = LoggerFactory.getLogger(AuthGlobalFilter.class);
    private static final AntPathMatcher pathMatch = new AntPathMatcher();
    private static final long EXPIRE_TIME = 5 * 60;
    private static final String NONCE_KEY = "hutan:";
    private static final char SEPARATOR = '&';
    private static final char CONNECTOR = '=';
    private static Map<String, String> ACCESSKEY = new HashMap<String, String>();

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        String sign = queryParams.getFirst("Sign");//签名
        String accessKey = queryParams.getFirst("AccessKey");//开发者标识
        String timestamp = queryParams.getFirst("Timestamp");//时间戳
        String nonce = queryParams.getFirst("Nonce");//随机数

        if (StringUtils.isEmpty(sign)) {
            log.info("未鉴权，拒绝请求 path:{}",request.getPath());
            return mono.just(new AuthorizationDecision(false));
        }

        //判断时间是否超过5分钟，如果超过，拒绝请求
        long timeDifference = StringUtils.isEmpty(timestamp) ? EXPIRE_TIME : Duration.between(LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.now()).getSeconds();
        if (timeDifference > EXPIRE_TIME) {
            log.info("请求携带的时间戳超过15分钟，拒绝请求");
            return mono.just(new AuthorizationDecision(false));
        }

        //判断nonce随机数是否已在系统中存在，若已存在，代表重复请求
        if (redisTemplate.hasKey(NONCE_KEY + nonce)) {
            log.info("重复请求，拒绝请求 path:{}");
            return mono.just(new AuthorizationDecision(false));
        }

        HashMap params = new HashMap();
        //判断参数传递的签名与计算的签名是否一致
        params.put("AccessKey", accessKey);
        params.put("Timestamp", timestamp);
        params.put("Nonce", nonce);
        params.put("SecretKey", ACCESSKEY.get(accessKey));
        params.put("Method", request.getMethod());
        boolean compareSign = false;
        try {
            compareSign = compareSign(params, sign);
        } catch (Exception e) {
            return mono.just(new AuthorizationDecision(false));
        }
        if (!compareSign) {
            log.info("签名不一致，拒绝请求 path:{}");
            return mono.just(new AuthorizationDecision(false));
        }

        //验证通过，记录nonce
        redisTemplate.opsForValue().set(NONCE_KEY + nonce, "");
        redisTemplate.expire(NONCE_KEY + nonce, EXPIRE_TIME, TimeUnit.SECONDS);
        return mono.just(new AuthorizationDecision(true));
    }

    /**
     * 重新进行MD5计算，并将计算结果与签名进行比对，若结果一致，代表校验通过，否则鉴权不通过
     * @param params
     * @param sign
     * @return
     * @throws NoSuchAlgorithmException
     */
    private boolean compareSign(Map<String, String> params, String sign) throws Exception {
        Objects.requireNonNull(sign);
        String[] sortedKeys = params.keySet().toArray(new String[]{});
        Arrays.sort(sortedKeys);
        StringBuilder canonicalizedQueryString = new StringBuilder();
        for (String key : sortedKeys) {
            // 这里注意对key和value进行编码
            canonicalizedQueryString.append(SEPARATOR)
                    .append(key).append(CONNECTOR)
                    .append(params.get(key));
        }
        canonicalizedQueryString = canonicalizedQueryString.deleteCharAt(0);
//        log.info("canonicalizedQueryString:{}",canonicalizedQueryString);
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(canonicalizedQueryString.toString().getBytes());
        String relSign = new String(Hex.encodeHex(md5.digest()));
        log.info(relSign);
        return sign.equals(relSign);
    }
}
