package com.haozi.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/3 1:25 下午
 */
public class ParamMissingException extends InternalException {

    private String filedName;
    private String sence;

    public ParamMissingException(String filedName, String sence) {
        super();
        this.filedName = filedName;
        this.sence = sence;
    }

    @Override
    public String toString() {
        return StrUtil.format("在{}场景下，字段{}为空",sence,filedName);
    }
}
