package com.haozi.auth.authentication;

import com.haozi.auth.authentication.check.AuthenticationCheck;
import com.haozi.auth.authentication.provider.AuthProvider;
import com.haozi.auth.authentication.token.BaseToken;
import com.haozi.common.exception.UnsupportParamExpection;
import com.haozi.common.model.dto.account.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 10:57 下午
 */
@Service
public class AuthenticationManager implements IAuthenticationManager {

    @Autowired
    List<AuthProvider> authProviderList;
    @Autowired
    List<AuthenticationCheck> checks;

    @Override
    public AccountInfo auth(String principal, String pwd, String loginType) {
        BaseToken baseToken = null;
        for (AuthProvider authProvider : authProviderList) {
            if (authProvider.support(loginType)) {
                baseToken = authProvider.auth(principal, pwd);
                break;
            }
            throw new UnsupportParamExpection("loginType", "认证", "未找到该登录类型的处理器,检查loginType是否正确");
        }
        for (AuthenticationCheck check : checks) {
            check.check(baseToken);
        }
        return baseToken.getAccountInfo();
    }
}
