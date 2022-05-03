package com.haozi.common.exception.internal;

import cn.hutool.core.util.StrUtil;

/**
 * 服务调用异常
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 10:39 下午
 */
public class ServiceCallException extends InternalException {

    /**
     * 服务名
     */
    public String sourceName;
    /**
     * 目标服务名
     */
    public String targetName;
    /**
     * 场景
     */
    public String sence;
    /**
     *
     * @param sourceName 源服务
     * @param targetName 目标服务
     * @param sence 场景
     */
    public ServiceCallException(String sourceName,String targetName,String sence){
        super("服务调用失败");
        this.sourceName = sourceName;
        this.targetName = targetName;
        this.sence = sence;
    }

    @Override
    public String toString() {
        return StrUtil.format("服务调用失败，源服务名:{}，目标服务{}，场景:{}",sourceName,targetName,sence);
    }
}
