package com.haozi.auth.authentication;

import cn.hutool.extra.spring.SpringUtil;
import com.haozi.auth.authentication.check.AuthenticationCheck;
import com.haozi.auth.authentication.provider.AuthProvider;
import com.haozi.auth.authentication.token.BaseToken;
import com.haozi.common.exception.Layer.LayerParamException;
import com.haozi.common.model.dto.auth.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

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

    ConcurrentHashMap<String, AuthProvider>
            cacheLoginProvider = new ConcurrentHashMap<>();

    @Override
    public AccountInfo auth(String principal, String pwd, String loginType) {
        BaseToken baseToken = null;
        //登录处理器
        AuthProvider loginProvider = getLoginProvider(loginType);
        if (loginProvider == null) {
            throw new LayerParamException("loginType", "未找到该登录类型的处理器,检查loginType是否正确");
        }

        baseToken = loginProvider.auth(principal, pwd);

        for (AuthenticationCheck check : checks) {
            check.check(baseToken);
        }
        return baseToken.getAccountInfo();
    }

    /**
     * 获取登录处理器
     *
     * @param loginType
     */
    private AuthProvider getLoginProvider(String loginType) {
        AuthProvider loginTypeProvider = cacheLoginProvider.get(loginType);

        if (loginTypeProvider != null) {
            return loginTypeProvider;
        }

        if (authProviderList.size() == cacheLoginProvider.size()) {
            return null;
        }
        AuthProvider provider = authProviderList
                .stream()
                .filter(authProvider -> authProvider.support(loginType))
                .findAny()
                .orElse(null);

        if (provider != null) {
            cacheLoginProvider.putIfAbsent(loginType, provider);
            return provider;
        }
        return null;
    }
}
