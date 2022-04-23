package com.haozi.annotation;

import java.lang.annotation.*;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/22 3:25 下午
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SnowId {
}
