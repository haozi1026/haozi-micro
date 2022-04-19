package com.haozi.common.exception.internal;

import cn.hutool.core.util.StrUtil;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/3 1:23 下午
 */
public class InternalException extends RuntimeException {

    private String reason;

    public InternalException(String reason){
        super("系统内部异常"+reason);
        this.reason = reason;
    }
    public InternalException(){
        super("系统内部异常");
    }

    @Override
    public String toString() {
        return StrUtil.format("系统内部异常:原因{}",reason);
    }
}
