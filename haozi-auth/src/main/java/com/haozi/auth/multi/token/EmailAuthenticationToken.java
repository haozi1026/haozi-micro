package com.haozi.auth.multi.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 2:36 下午
 */
public class EmailAuthenticationToken extends AbstractAuthenticationToken {

    private String email;

    private String password;

    public EmailAuthenticationToken(String email,String password){
        super(null);
        this.email = email;
        this.password = password;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return email;
    }
}
