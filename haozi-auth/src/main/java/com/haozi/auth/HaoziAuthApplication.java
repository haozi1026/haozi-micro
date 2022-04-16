package com.haozi.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HaoziAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaoziAuthApplication.class, args);
    }

}
