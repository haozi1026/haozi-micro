package com.haozi.common.util;

import com.haozi.common.exception.internal.ParamMissingException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/23 12:33 下午
 */
public class ValidationUtil {

    private static Validator validator = Validation
            .buildDefaultValidatorFactory()
            .getValidator();

    /**
     * jsr303校验
     * @param obj 对象
     * @param sence 场景
     * @return
     */
    public static ParamMissingException verifyJsr303(Object obj,String sence){

        Set<ConstraintViolation<Object>> validate = validator.validate(obj);
        StringBuilder stringBuilder = new StringBuilder();
        for (ConstraintViolation<Object> objectConstraintViolation : validate) {
            Object invalidValue = objectConstraintViolation.getInvalidValue();
            stringBuilder.append(invalidValue).append(" ");
        }

        if(validate.size()>0){
            return new ParamMissingException(stringBuilder.toString(),sence);
        }
        return null;
    }

}
