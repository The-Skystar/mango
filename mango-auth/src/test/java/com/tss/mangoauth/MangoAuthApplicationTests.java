package com.tss.mangoauth;

import com.tss.mangoauth.domain.UrlAccessBO;
import com.tss.mangoauth.mapper.RoleMapper;
import com.tss.mangoauth.mapper.UrlRouteMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MangoAuthApplicationTests {
    @Autowired
    private UrlRouteMapper routeMapper;

    @Test
    void contextLoads() {
        List<UrlAccessBO> urlAccess = routeMapper.getUrlAccess();
        urlAccess.forEach(System.out::println);
    }

}
