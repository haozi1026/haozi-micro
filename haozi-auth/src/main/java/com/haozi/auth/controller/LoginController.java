package com.haozi.auth.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 1:57 下午
 */
@RestController
public class LoginController {

    /**
     * 登录之后的事
     */
    @PostMapping("/after_login")
    public void token() {
        //获取到当前登录用户
        SecurityContext context = SecurityContextHolder.getContext();

        //生成access-token

        //生成renew-token

        //用户信息存入 redis

    }

}
