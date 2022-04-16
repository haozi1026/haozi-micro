package com.haozi.auth.multi.filter;

import cn.hutool.extra.spring.SpringUtil;
import com.haozi.auth.multi.provider.AuthenticationProvider;
import com.haozi.auth.multi.token.EmailAuthenticationToken;
import com.haozi.common.constants.StatusEnum;
import com.haozi.common.exception.InternalException;
import com.haozi.common.model.ResponseResult;
import com.haozi.common.model.dto.account.AccountInfo;
import com.haozi.common.model.dto.account.EmailRequest;
import com.haozi.support.openfegin.account.AccountClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * email 登录方式
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 2:29 下午
 */
@Slf4j
public class MultiAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login",
            "POST");

    protected MultiAuthenticationFilter() {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }

    Map<String, AuthenticationProvider> beansOfType =
            SpringUtil.getBeansOfType(AuthenticationProvider.class);




    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        final Authentication[] authentication = {null};

        beansOfType.forEach((key,value)->{

            if(value.support(request)){
                authentication[0] = value.authentication(request);
                return;
            }

            throw new InternalException("认证失败：登录类型错误");

        });

        return authentication[0];
    }

}
