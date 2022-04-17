package com.haozi.auth.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.haozi.auth.dao.po.TokenConfig;
import com.haozi.auth.exception.AuthException;
import com.haozi.auth.exception.NoUserException;
import com.haozi.auth.handler.FailLoginHandler;
import com.haozi.auth.handler.SuccessLoginHandler;
import com.haozi.auth.service.AuthService;
import com.haozi.auth.service.ITokenConfigService;
import com.haozi.common.constants.AuthConstants;
import com.haozi.common.exception.ConfigException;
import com.haozi.common.exception.ParamMissingException;
import com.haozi.common.model.ResponseResult;
import com.haozi.common.model.dto.account.AccountInfo;
import com.haozi.common.model.dto.account.LoginResponse;
import com.haozi.common.model.dto.token.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import support.cache.account.AccountInfoCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    AccountInfoCache accountCache;
    @Autowired
    ITokenConfigService tokenConfigService;

    @PostMapping("/login")
    public ResponseResult<LoginResponse> login(String principal, String pwd, String loginType) {
        AccountInfo accountInfo;
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

        try {
            //认证
            accountInfo =  authService.auth(principal, pwd, loginType);
        } catch (AuthException | NoUserException exception) {
            failLoginHandler.handler(request,principal);
            LoginResponse fail = LoginResponse.fail();
            return ResponseResult.success(fail);
        }
        //成功后处理
        successLoginHandler.handler(request,principal);
        //token配置
        TokenConfig tokenConfig = tokenConfigService.tokenConfig();
        if(tokenConfig == null){
            throw new ConfigException("token配置","缺失");
        }
        //token 生成
        String loginId = IdUtil.fastSimpleUUID();
        TokenInfo tokenInfo = generateTokenInfo(loginId, tokenConfig);
        //账号信息存缓存
        accountCache.cache(loginId,accountInfo,tokenConfig.getAccessTokenExp()*2+3);

        //组装token信息token
        LoginResponse loginSuccess = LoginResponse.success(tokenInfo, loginId);

        return ResponseResult.success(loginSuccess);
    }

    private TokenInfo generateTokenInfo(String loginId,TokenConfig tokenConfig){

        JWT accessToken = JWT.create()
                //主体
                .setPayload(AuthConstants.ACCESS_TOKEN_PAY_LOAD, loginId)
                //失效时间
                .setExpiresAt(DateUtil.offset(new DateTime(), DateField.MINUTE, tokenConfig.getAccessTokenExp()));


        JWT renewToken = JWT.create()
                //主体
                .setPayload(AuthConstants.RENEW_TOKEN_PAY_LOAD, loginId)
                //失效时间
                .setExpiresAt(DateUtil.offset(new DateTime(), DateField.MINUTE, tokenConfig.getAccessTokenExp()*2));

        TokenInfo tokenInfo = new TokenInfo(accessToken.sign(),renewToken.sign());

        return tokenInfo;
    }
}
