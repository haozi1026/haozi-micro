package com.haozi.auth.exception;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 10:30 下午
 */
public class AuthException extends RuntimeException {

    private String principal;

    public AuthException(String principal){
        super("账号或密码错误");
        this.principal = principal;
    }

}
