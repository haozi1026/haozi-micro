package com.haozi.auth.exception;

import com.haozi.common.exception.BizException;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 7:06 上午
 */
public class AccountException extends BizException {
    String message;

    public AccountException(String message){
        this.message = "账号异常"+message;
    }

    @Override
    public String toString() {
        return message;
    }
}
