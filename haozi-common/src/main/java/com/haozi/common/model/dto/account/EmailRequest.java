package com.haozi.common.model.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 3:10 下午
 */
@Data
@AllArgsConstructor
public class EmailRequest {

    /**
     * 邮箱地址
     */
    public String email;

}
