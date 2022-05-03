package com.haozi.common.model.dto.auth;

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

    public EmailRequest(){}

    /**
     * 邮箱地址
     */
    private String email;

}
