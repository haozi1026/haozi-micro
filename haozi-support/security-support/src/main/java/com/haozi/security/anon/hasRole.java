package com.haozi.security.anon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/5/3 2:45 下午
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface hasRole {
    /**
     * 角色
     * @return
     */
    String value();
}
