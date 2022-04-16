package com.haozi.auth.config;

import com.haozi.auth.authentication.handler.LoginFailure;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 10:42 上午
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .successForwardUrl("token")
                .failureHandler(authenticationFailureHandler())
                .permitAll();

        super.configure(http);
    }

    public AuthenticationFailureHandler authenticationFailureHandler() {

        return new LoginFailure();
    }
}
