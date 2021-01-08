package com.tss.mangoauth.service;

import com.tss.mangoauth.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 资源与角色匹配关系管理业务类
 * Created by yangxiangjun on 2020/6/19.
 */
@Service
public class ResourceServiceImpl {

    private Map<String, List<String>> resourceRolesMap;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @PostConstruct
    public void initData() {
        resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/api/hello", Collections.singletonList("ADMIN"));
        resourceRolesMap.put("/api/user/currentUser", Arrays.asList("ADMIN", "TEST"));
        resourceRolesMap.put("/service-a/addjob", Collections.singletonList("ADMIN"));
        resourceRolesMap.put("/service-a/user/currentUser",Collections.singletonList("ADMIN"));
        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}
