package com.haozi.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/3/1 9:40 下午
 */
@AllArgsConstructor
@Getter
public enum StatusEnum {

    /**
     * 成功
     */
    SUCCESS(1,"成功"),

    /**
     * 失败
     */
    fail(0,"失败");


    int code;
    String message;
}
