package com.haozi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.haozi.account.dao.mapper")
public class HaoziAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaoziAccountApplication.class, args);
    }

}
