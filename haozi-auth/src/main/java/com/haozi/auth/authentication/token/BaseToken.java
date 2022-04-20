package com.haozi.auth.authentication.token;

import com.haozi.common.model.dto.auth.AccountInfo;
import lombok.Getter;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 6:51 上午
 */
@Getter
public class BaseToken {
    /**
     * 登录主体
     */
    private String principal;

    /**
     * 账号信息
     */
    private AccountInfo accountInfo;

    public BaseToken(String principal,AccountInfo accountInfo){
        this.principal = principal;
        this.accountInfo = accountInfo;
    }

}
