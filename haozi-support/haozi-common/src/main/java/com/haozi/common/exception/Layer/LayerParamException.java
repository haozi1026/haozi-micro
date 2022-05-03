package com.haozi.common.exception.Layer;

import cn.hutool.core.util.StrUtil;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 3:27 下午
 */
public class LayerParamException extends LayerException {

    /**
     * 字段名
     */
   private String paramField;
    /**
     * 发生场景
     */
   private String sence;
    public LayerParamException(String paramField,String sence){
        super("参数错误");
        this.paramField = paramField;
        this.sence = sence;
    }

    @Override
    public String toString() {
        return StrUtil.format("传参错误:{},不能为空,场景:{}",paramField,sence);
    }
}
