package com.tss.mangoservicea.manager;


import com.tss.mangocommon.feign.RequestHeaderInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 获取登录用户信息
 * Created by yangxiangjun on 2020/6/17.
 */
@FeignClient(value = "mango-activiti",configuration = RequestHeaderInterceptor.class)
public interface LoginUserHolder {

    @PostMapping("/wkf/queryDefinition")
    void test();
}
