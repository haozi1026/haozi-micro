package com.haozi.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haozi.common.exception.InternalException;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/19 10:36 上午
 */
public class jackSonUtil {

    /**
     * 对象转json
     * @param object
     * @return
     */
    public static String toJson(Object object){

        if(object == null){
            return  null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        //将对象转为JSON串
        try {
            String jsonString = objectMapper.writeValueAsString(object);
            return jsonString;
        } catch (JsonProcessingException e) {
            throw new InternalException("序列化json异常");
        }
    }

}
