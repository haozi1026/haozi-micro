package com.haozi.auth.authentication.token;

import com.haozi.common.model.dto.account.AccountInfo;
import lombok.Getter;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 6:52 上午
 */
@Getter
public class PasswordToken extends BaseToken {
    /**
     * 密码
     */
    private String pwd;

    public PasswordToken(String principal, AccountInfo accountInfo,String pwd) {
        super(principal, accountInfo);
        this.pwd = pwd;
    }
}
