package com.haozi.auth.authentication.check.impl;

import com.haozi.auth.authentication.check.AuthenticationCheck;
import com.haozi.auth.authentication.token.BaseToken;
import com.haozi.auth.authentication.token.PasswordToken;
import com.haozi.auth.exception.AuthException;
import com.haozi.common.model.dto.account.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 7:10 上午
 */
@Component
public class PwdCheck implements AuthenticationCheck {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void check(BaseToken baseToken) {
        if(baseToken instanceof PasswordToken == false){
            return;
        }
        AccountInfo accountInfo = baseToken.getAccountInfo();
        PasswordToken passwordToken = (PasswordToken)baseToken;
        String pwd = passwordToken.getPwd();

        boolean matches = passwordEncoder.matches(pwd, accountInfo.getPwd());

        if(!matches){
            throw new AuthException("密码错误");
        }

    }
}
