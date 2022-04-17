package com.haozi.auth.service.impl;

import com.haozi.auth.authentication.AuthenticationManager;
import com.haozi.auth.exception.AuthException;
import com.haozi.auth.service.AuthService;
import com.haozi.common.model.dto.account.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 10:29 下午
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public AccountInfo auth(String principal, String pwd, String loginType) {
        return authenticationManager.auth(principal, pwd, loginType);
    }
}
