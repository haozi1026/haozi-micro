package com.haozi.common.exception.biz;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 11:02 下午
 */
public class BizException extends RuntimeException {

    private String message;
    public BizException(){
        super("业务异常");
    }
    public BizException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
