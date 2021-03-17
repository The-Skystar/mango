package com.tss.mangoauth;

import com.tss.mangoauth.domain.UrlAccessBO;
import com.tss.mangoauth.entity.UserEntity;
import com.tss.mangoauth.mapper.RoleMapper;
import com.tss.mangoauth.mapper.UrlRouteMapper;
import com.tss.mangoauth.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MangoAuthApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() throws ParseException {
        Date date = new Date();
        SimpleDateFormat r = new SimpleDateFormat("yyyy-MM-dd");
        String format = r.format(date);
        Date parse = r.parse(format);
        List<UserEntity> query = userMapper.query(parse);
        query.forEach(userEntity -> System.out.println(userEntity.toString()));
    }

}
