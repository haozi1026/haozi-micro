package com.haozi.common.model.vo;

import lombok.Data;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/3 9:59 上午
 */
@Data
public class AuthResult {

    /**
     * 是否验证通过
     */
    private boolean pass;
    /**
     * 访问 token
     */
    private String AccessToken;
    /**
     * 续约token
     */
    private String refreshToken;

}
