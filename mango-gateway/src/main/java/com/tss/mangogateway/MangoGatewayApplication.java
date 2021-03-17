package com.tss.mangogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.tss")
public class MangoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoGatewayApplication.class, args);
    }

}
