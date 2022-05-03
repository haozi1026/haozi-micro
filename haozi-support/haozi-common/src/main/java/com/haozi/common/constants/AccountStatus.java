package com.haozi.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 7:01 上午
 */
@AllArgsConstructor
@Getter
public enum AccountStatus {
    /**
     * 禁用
     */
    DISABLE((byte) 0),
    /**
     * 可用
     */
    ENABLE((byte) 1);

    byte code;
}
