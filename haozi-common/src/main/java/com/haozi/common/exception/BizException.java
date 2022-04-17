package com.haozi.common.exception;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 11:02 下午
 */
public class BizException extends RuntimeException {

    public BizException(){
        super("业务异常");
    }
}
