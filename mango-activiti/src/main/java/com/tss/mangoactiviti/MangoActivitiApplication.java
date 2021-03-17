package com.tss.mangoactiviti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.tss")
@EnableFeignClients
public class MangoActivitiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoActivitiApplication.class, args);
	}

}
