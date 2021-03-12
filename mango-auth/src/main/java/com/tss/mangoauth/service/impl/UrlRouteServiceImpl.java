package com.tss.mangoauth.service.impl;

import com.tss.mangoauth.constant.RedisConstant;
import com.tss.mangoauth.domain.UrlAccessBO;
import com.tss.mangoauth.entity.UrlRoute;
import com.tss.mangoauth.mapper.UrlRouteMapper;
import com.tss.mangoauth.service.UrlRouteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * <p>
 * API接口表 服务实现类
 * </p>
 *
 * @author 
 * @since 2021-03-08
 */
@Service
public class UrlRouteServiceImpl extends ServiceImpl<UrlRouteMapper, UrlRoute> implements UrlRouteService {

    private Map<String, List<String>> resourceRolesMap;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private UrlRouteMapper routeMapper;

    /**
     * 将每一个接口对应有哪些角色能够访问的关系维护到redis缓存中
     */
    @PostConstruct
    public void initData() {
        resourceRolesMap = new ConcurrentHashMap<>();
        List<UrlAccessBO> urlAccess = routeMapper.getUrlAccess();
        urlAccess.forEach(a -> {
            List<String> roles = a.getRoles().stream().map(role -> role.getRoleCode()).collect(Collectors.toList());
            resourceRolesMap.put(a.getRoute(),roles);
        });
        redisTemplate.boundHashOps(RedisConstant.RESOURCE_ROLES_MAP).putAll(resourceRolesMap);
    }
}
