package com.haozi.auth.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import com.haozi.account.manager.AccountManager;
import com.haozi.auth.dao.po.TokenConfig;
import com.haozi.auth.exception.AuthException;
import com.haozi.auth.exception.NoUserException;
import com.haozi.auth.handler.FailLoginHandler;
import com.haozi.auth.handler.SuccessLoginHandler;
import com.haozi.auth.service.AuthService;
import com.haozi.auth.service.ITokenConfigService;
import com.haozi.common.constants.AuthConstants;
import com.haozi.common.exception.BizException;
import com.haozi.common.exception.ConfigException;
import com.haozi.common.exception.Layer.LayerParamException;
import com.haozi.common.model.ResponseResult;
import com.haozi.common.model.dto.account.AccountInfo;
import com.haozi.common.model.dto.account.LoginResponse;
import com.haozi.common.model.dto.token.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 10:20 下午
 */
@RestController
@RequestMapping("/authentication/")
public class AuthenticationController {

    @Autowired
    AuthService authService;
    @Autowired
    FailLoginHandler failLoginHandler;
    @Autowired
    SuccessLoginHandler successLoginHandler;
    @Autowired
    AccountManager accountManager;
    @Autowired
    ITokenConfigService tokenConfigService;

    @PostMapping("/login")
    public ResponseResult<LoginResponse> login(String principal, String pwd, String loginType) {
        AccountInfo accountInfo;
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

        if (StrUtil.isBlank(principal)) {
            throw new LayerParamException("principal","登录");
        }
        if (StrUtil.isBlank(pwd)) {
            throw new LayerParamException("pwd","登录");
        }
        if (StrUtil.isBlank(loginType)) {
            throw new LayerParamException("loginType","登录");
        }

        try {
            //认证
            accountInfo = authService.auth(principal, pwd, loginType);
        } catch (AuthException | NoUserException exception) {
            failLoginHandler.handler(request, principal);
            LoginResponse fail = LoginResponse.fail();
            return ResponseResult.success(fail);
        }
        //成功后处理
        successLoginHandler.handler(request, principal);
        //token配置
        TokenConfig tokenConfig = tokenConfigService.tokenConfig();
        if (tokenConfig == null) {
            throw new ConfigException("token配置", "缺失");
        }
        //token 生成
        String loginId = IdUtil.fastSimpleUUID();
        TokenInfo tokenInfo = generateTokenInfo(loginId, tokenConfig);
        //账号信息存缓存
        accountManager.saveAccountinfo(loginId, accountInfo, tokenConfig.getAccessTokenExp() * 2 + 3);
        //组装token信息token
        LoginResponse loginSuccess = LoginResponse.success(tokenInfo, loginId);

        return ResponseResult.success(loginSuccess);
    }

    private TokenInfo generateTokenInfo(String loginId, TokenConfig tokenConfig) {

        if(StrUtil.isBlank(tokenConfig.getAccessTokenKey())){
            throw new ConfigException("jwt sign","jwt sign缺失");
        }
        if(StrUtil.isBlank(tokenConfig.getRefreshTokenKey())){
            throw new ConfigException("jwt sign","jwt sign缺失");
        }
        if(tokenConfig.getAccessTokenExp() == null){
            throw new ConfigException("jwt 有效时间","jwt 有效时间缺失");
        }

        JWT accessToken = JWT.create()
                .setKey(tokenConfig.getAccessTokenKey().getBytes(StandardCharsets.UTF_8))
                //主体
                .setPayload(AuthConstants.ACCESS_TOKEN_PAY_LOAD, loginId)
                //失效时间
                .setExpiresAt(DateUtil.offset(new DateTime(), DateField.MINUTE, tokenConfig.getAccessTokenExp()));


        JWT renewToken = JWT.create()
                .setKey(tokenConfig.getRefreshTokenKey().getBytes(StandardCharsets.UTF_8))
                //主体
                .setPayload(AuthConstants.RENEW_TOKEN_PAY_LOAD, loginId)
                //失效时间
                .setExpiresAt(DateUtil.offset(new DateTime(), DateField.MINUTE, tokenConfig.getAccessTokenExp() * 2));

        TokenInfo tokenInfo = new TokenInfo(accessToken.sign(), renewToken.sign());

        return tokenInfo;
    }
}