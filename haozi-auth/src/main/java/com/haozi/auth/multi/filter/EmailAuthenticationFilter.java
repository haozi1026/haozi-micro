package com.haozi.auth.multi.filter;

import com.haozi.auth.multi.token.EmailAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * email 登录方式
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 2:29 下午
 */
public class EmailAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login",
            "POST");

    protected EmailAuthenticationFilter() {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }
    private boolean postOnly = true;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String email = request.getParameter(EMAIL);
        email = (email != null) ? email : "";
        email = email.trim();
        String password = request.getParameter(PASSWORD);
        EmailAuthenticationToken token = new EmailAuthenticationToken(email,password);



        return token;
    }

    private final String EMAIL = "email";

    private String PASSWORD = "password";
}
