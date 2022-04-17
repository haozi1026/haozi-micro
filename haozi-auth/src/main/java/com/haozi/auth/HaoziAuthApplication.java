package com.haozi.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import support.cache.EnableRedisSupport;

@SpringBootApplication
@EnableFeignClients
@EnableRedisSupport
@MapperScan(basePackages = "com.haozi.auth.dao.mapper")
public class HaoziAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaoziAuthApplication.class, args);
    }

}
