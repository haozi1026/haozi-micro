package com.haozi.common.exception;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 11:03 下午
 */
public class UnsupportParamExpection extends BizException {

    /**
     * 参数名
     */
    private String paramName;
    /**
     * 场景
     */
    private String sence;
    /**
     * 描述
     */
    private String desc;

    public UnsupportParamExpection(String paramName,String sence,String desc){
        super();
        this.paramName = paramName;
        this.sence = sence;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return StrUtil.format("不支持该参数:参数名：{}，场景:{},描述:{}",paramName, sence,desc);
    }
}
