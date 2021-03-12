package com.tss.mangoauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tss.mangoauth.mapper")
public class MangoAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoAuthApplication.class, args);
    }

}
