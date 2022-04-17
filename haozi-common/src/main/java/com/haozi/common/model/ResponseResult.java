package com.haozi.common.model;

import com.haozi.common.constants.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/3/1 9:38 下午
 */
@Data
@AllArgsConstructor
public class ResponseResult<T> {

    private int code;
    private String message;
    private T data;
    public ResponseResult(){};

    public boolean isSuccess(){
        return StatusEnum.SUCCESS.getCode() == getCode();
    }
    /**
     * 成功
      * @param data
     * @param <T>
     * @return
     */
   public static <T> ResponseResult<T> success(T data){
       return new ResponseResult(StatusEnum.SUCCESS.getCode(),StatusEnum.SUCCESS.getMessage(),data);
    }

    /**
     * 失败
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> fail(T data){
        return new ResponseResult(StatusEnum.fail.getCode(),StatusEnum.fail.getMessage(),data);
    }

}
