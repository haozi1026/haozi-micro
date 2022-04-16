package com.haozi.auth.multi.provider;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 5:30 下午
 */
public interface AuthenticationProvider {


    /**
     * 认证
     * @param token
     * @return
     */
    Authentication authentication(HttpServletRequest request);

    /**
     * 是否支持
     * @param request
     * @return
     */
    boolean support(HttpServletRequest request);

}
