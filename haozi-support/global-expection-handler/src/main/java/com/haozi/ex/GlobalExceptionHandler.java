package com.haozi.ex;

import com.haozi.common.exception.internal.ConfigException;
import com.haozi.common.exception.Layer.LayerException;
import com.haozi.common.model.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 3:37 下午
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public GlobalExceptionHandler(){

    }

    @ExceptionHandler(value = LayerException.class)
    public ResponseResult layerException(LayerException layerException){
        log.error(layerException.toString());
        return ResponseResult.fail("系统繁忙，稍后重试");
    }
    @ExceptionHandler(value = ConfigException.class)
    public ResponseResult configException(ConfigException exception){
        log.error(exception.toString());
        return ResponseResult.fail("系统繁忙，稍后重试");
    }
}
