package com.haozi.common.model.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/19 4:23 下午
 */
@Data
@AllArgsConstructor
public class PhoneRequest {
    public PhoneRequest(){};
    /**
     * 手机号
     */
    private String phone;

}
