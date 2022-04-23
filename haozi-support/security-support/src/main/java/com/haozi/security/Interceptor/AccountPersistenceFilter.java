package com.haozi.security.Interceptor;

import cn.hutool.core.util.StrUtil;
import com.haozi.account.manager.AccountManager;
import com.haozi.common.constants.AuthConstants;
import com.haozi.common.model.dto.auth.AccountInfo;
import com.haozi.security.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/20 11:39 上午
 */
//@Component
public class AccountPersistenceFilter extends GenericFilterBean {

    @Autowired
    AccountManager accountManager;
    @Autowired
    SecurityContextHolder securityContextHolder;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
          //取 请求头里的loginId
        HttpServletRequest httpServletRequest= (HttpServletRequest)servletRequest;
        String loginId = httpServletRequest.getHeader(AuthConstants.HEADER_LOGIN_ID);
        if(StrUtil.isBlank(loginId)){
            return;
        }
        Optional<AccountInfo> optionalAccountInfo = accountManager.getAccountInfo(loginId);
        AccountInfo accountInfo = optionalAccountInfo.get();

        try {
            securityContextHolder.setContext(accountInfo);
            filterChain.doFilter(servletRequest,servletResponse);
        } finally {
            securityContextHolder.clearContext();
        }
    }
}
