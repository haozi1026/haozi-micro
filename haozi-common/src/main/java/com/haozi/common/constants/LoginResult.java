package com.haozi.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 6:25 上午
 */
@AllArgsConstructor
@Getter
public enum LoginResult {

    /**
     * 成功
     */
    SUCCESS((byte) 1),
    /**
     * 失败
     */
    FAIL((byte) 0);


    private byte code;



}
