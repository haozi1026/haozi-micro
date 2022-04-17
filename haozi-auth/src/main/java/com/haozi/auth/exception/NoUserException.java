package com.haozi.auth.exception;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 6:08 上午
 */
public class NoUserException extends RuntimeException  {

    private String principal;

    public NoUserException(String principal){
        super("账号不存在");
        this.principal = principal;
    }

}
