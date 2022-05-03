package com.haozi.common.model.dto.token;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 10:22 下午
 */
@Data
@AllArgsConstructor
public class TokenInfo {

    /**
     * 访问token
     */
    private String accessToken;
    /**
     * 续约token
     */
    private String renewToken;

}
