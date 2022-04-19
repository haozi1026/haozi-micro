package com.haozi.security.anon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/19 5:02 下午
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface hasPermission {

    /**
     * 资源标识符
     * @return
     */
    String resourceFlag();

    String value();
}
