package com.haozi.ex;

import com.haozi.common.exception.biz.AccessDeniedException;
import com.haozi.common.exception.biz.BizException;
import com.haozi.common.exception.internal.ConfigException;
import com.haozi.common.exception.Layer.LayerException;
import com.haozi.common.exception.internal.InternalException;
import com.haozi.common.model.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


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
    /**
     * 前端-后端 过程异常
     * @param layerException
     * @return
     */
    @ExceptionHandler(value = LayerException.class)
    public ResponseResult layerException(LayerException layerException){
        log.error(layerException.toString());
        return ResponseResult.fail("系统繁忙，稍后重试");
    }
    /**
     * 配置异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = ConfigException.class)
    public ResponseResult configException(ConfigException exception){
        log.error(exception.toString());
        return ResponseResult.fail("系统繁忙，稍后重试");
    }

    /**
     * 鉴权异常
     * @param accessDeniedException
     * @return
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseResult accessDeniedException(AccessDeniedException accessDeniedException){
        return ResponseResult.fail(accessDeniedException.toString());
    }

    /**
     * 参数校验异常
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult bindException(BindException bindException){
        List<FieldError> fieldErrors = bindException.getBindingResult().getFieldErrors();

        StringBuilder stringBuilder = new StringBuilder();

        for (FieldError fieldError : fieldErrors) {
            stringBuilder.append(fieldError.getDefaultMessage());
        }
        return ResponseResult.fail(stringBuilder.toString());
    }

    /**
     * 内部错误异常
     * @param internalException
     * @return
     */
    @ExceptionHandler(InternalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult internaleException(InternalException internalException){
        log.error(internalException.toString());
        return ResponseResult.fail("服务器异常");
    }

    /**
     * 业务异常
     * @param exception
     * @return
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult bizException(BizException exception){
        return ResponseResult.fail(exception.toString());
    }
}

