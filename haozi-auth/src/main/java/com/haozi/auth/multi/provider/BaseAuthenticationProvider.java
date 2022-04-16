package com.haozi.auth.multi.provider;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 5:34 下午
 */
public abstract class BaseAuthenticationProvider implements  AuthenticationProvider {

    @Override
    public boolean support(HttpServletRequest request) {

        String parameter = request.getParameter(LOGIN_TYPE);

        return support(parameter);
    }

    protected abstract boolean support(String loginType);
    private final String LOGIN_TYPE = "login-type";
}
