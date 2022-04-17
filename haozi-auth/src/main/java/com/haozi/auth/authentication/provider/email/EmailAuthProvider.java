package com.haozi.auth.authentication.provider.email;

import com.haozi.auth.authentication.token.BaseToken;
import com.haozi.auth.authentication.token.PasswordToken;
import com.haozi.auth.exception.AuthException;
import com.haozi.auth.authentication.provider.AuthProvider;
import com.haozi.auth.exception.NoUserException;
import com.haozi.auth.remote.AccountClient;
import com.haozi.common.constants.ServiceName;
import com.haozi.common.exception.ServiceCallException;
import com.haozi.common.model.ResponseResult;
import com.haozi.common.model.dto.account.AccountInfo;
import com.haozi.common.model.dto.account.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 10:33 下午
 */
@Component
public class EmailAuthProvider implements AuthProvider {

    @Autowired
    AccountClient accountClient;


    @Override
    public boolean support(String loginType) {
        return EMAIL.equals(loginType);
    }

    @Override
    public PasswordToken auth(String principal, String pwd) {
        EmailRequest emailRequest = new EmailRequest(principal);
        //账户系统调用
        ResponseResult<AccountInfo> accountByEmail = accountClient.getAccountByEmail(emailRequest);

        if (!accountByEmail.isSuccess()) {
            throw new ServiceCallException(ServiceName.AUTH, ServiceName.ACCOUNT, "根据邮箱获取账户信息");
        }
        AccountInfo accountInfo = accountByEmail.getData();
        if (accountInfo == null) {
            throw new NoUserException(principal);
        }

        return new PasswordToken(accountInfo.getPrincipal(),accountInfo,pwd);
    }

    private final String EMAIL = "email";
}
