package com.haozi.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/4 10:42 上午
 */
public class ConfigException extends InternalException {

    private String configName;

    private String reason;

    public ConfigException(String configName,String reason){
        super();
        this.configName = configName;
        this.reason = reason;
    }

    @Override
    public String toString() {
        String msg = StrUtil.format("配置错误，配置项:{},原因:{}",configName,reason);
        return msg;

    }
}
