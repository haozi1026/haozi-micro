package com.haozi.auth.multi.provider.email;

import cn.hutool.extra.spring.SpringUtil;
import com.haozi.auth.multi.provider.BaseAuthenticationProvider;
import com.haozi.auth.multi.token.EmailAuthenticationToken;
import com.haozi.common.constants.StatusEnum;
import com.haozi.common.model.ResponseResult;
import com.haozi.common.model.dto.account.AccountInfo;
import com.haozi.common.model.dto.account.EmailRequest;
import com.haozi.support.openfegin.account.AccountClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 5:36 下午
 */
@Slf4j
@Component
public class EmailProvider extends BaseAuthenticationProvider {

    @Override
    public Authentication authentication(HttpServletRequest request) {

        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String email = request.getParameter(EMAIL);
        email = (email != null) ? email : "";
        email = email.trim();
        String password = request.getParameter(PASSWORD);
        EmailAuthenticationToken token = new EmailAuthenticationToken(email,password);

        AccountClient accountClient = SpringUtil.getBean(AccountClient.class);
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setEmail(email);

        ResponseResult<AccountInfo> accountByEmail =
                accountClient.getAccountByEmail(emailRequest);

        if(accountByEmail.getCode() != StatusEnum.SUCCESS.getCode()){
            log.error("查询账户信息错误");
            return null;
        }
        AccountInfo accountInfo = accountByEmail.getData();
        accountInfo.setPermission(accountInfo.getPermission());

        return token;
    }

    @Override
    protected boolean support(String loginType) {
        return EMAUL_TYPE.equals(loginType);
    }

    private final String EMAUL_TYPE = "email";

    private final String EMAIL = "email";

    private String PASSWORD = "password";

    private boolean postOnly = true;
}
