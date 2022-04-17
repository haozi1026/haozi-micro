package com.haozi.auth.authentication.check.impl;

import com.haozi.auth.authentication.check.AuthenticationCheck;
import com.haozi.auth.authentication.token.BaseToken;
import com.haozi.auth.exception.AccountException;
import com.haozi.common.constants.AccountStatus;
import com.haozi.common.model.dto.account.AccountInfo;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 6:59 上午
 */
public class AccountInfoCheck implements AuthenticationCheck {


    @Override
    public void check(BaseToken baseToken) {

        AccountInfo accountInfo = baseToken.getAccountInfo();
        if (AccountStatus.DISABLE.getCode() == accountInfo.getEnable()) {
            throw new AccountException("账号不可用");
        }
    }
}
