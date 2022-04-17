package com.haozi.common.exception.Layer;

/**
 * 展示层交互错误
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 3:26 下午
 */
public class LayerException extends RuntimeException {
    LayerException(String message){
        super(message);
    }
}
