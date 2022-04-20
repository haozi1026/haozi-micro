package com.haozi.auth.authentication.provider.email;

import com.haozi.auth.authentication.provider.AuthProvider;
import com.haozi.auth.authentication.token.BaseToken;
import com.haozi.auth.authentication.token.PasswordToken;
import com.haozi.auth.exception.NoUserException;
import com.haozi.auth.remote.AccountClient;
import com.haozi.common.constants.ServiceName;
import com.haozi.common.exception.internal.ServiceCallException;
import com.haozi.common.model.ResponseResult;
import com.haozi.common.model.dto.auth.AccountInfo;
import com.haozi.common.model.dto.auth.PhoneRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/19 4:35 下午
 */
@Component
public class PhoneAuthProvider implements AuthProvider {

    @Autowired
    AccountClient accountClient;

    @Override
    public boolean support(String loginType) {
        return PHONE.equals(loginType);
    }

    @Override
    public BaseToken auth(String principal, String pwd) {
        PhoneRequest phoneRequest = new PhoneRequest(principal);
        //账户系统调用
        ResponseResult<AccountInfo> accountByEmail = accountClient.getAccountByPhone(phoneRequest);

        if (!accountByEmail.isSuccess()) {
            throw new ServiceCallException(ServiceName.AUTH, ServiceName.ACCOUNT, "根据手机号获取账户信息");
        }
        AccountInfo accountInfo = accountByEmail.getData();
        if (accountInfo == null) {
            throw new NoUserException(principal);
        }

        return new PasswordToken(accountInfo.getPrincipal(),accountInfo,pwd);
    }
    private final String PHONE = "phone";
}
