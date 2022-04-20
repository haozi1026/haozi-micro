package com.haozi.gw.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.AntPathMatcher;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import com.haozi.account.manager.AccountManager;
import com.haozi.common.constants.AuthConstants;
import com.haozi.common.exception.biz.AccessDeniedException;
import com.haozi.common.model.dto.auth.AccountInfo;
import com.haozi.gw.config.AnonymousConfig;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/18 7:43 下午
 */
@Component
@Slf4j
public class AuthFilter extends ZuulFilter {

    Pattern pattern = null;
    @Autowired
    AccountManager accountManager;
    @Autowired
    AnonymousConfig anonymousConfig;
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    public AuthFilter(ZuulProperties zuulProperties) {
        String prefix = zuulProperties.getPrefix();
        String regStr = prefix + "(.*)";
        pattern = Pattern.compile(regStr);
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext currentContext = RequestContext.getCurrentContext();
        StringBuffer requestURL = currentContext.getRequest().getRequestURL();
        //获取目标地址
        String requestUrl = ReUtil.get(pattern, requestURL.toString(), 1);

        if (isAllowAnon(requestUrl)) {
            return null;
        }
        //取访问 token
        String accessToken = currentContext.getRequest().getHeader("");
        if (StrUtil.isBlank(accessToken)) {
            throw new AccessDeniedException(AccessDeniedException.Type.NOT_LOGIN);
        }
        //token 校验
        JWT jwt = null;
        try {
            jwt = JWTUtil.parseToken(accessToken);
            JWTValidator.of(jwt).validateDate();
        } catch (Exception ex) {
            // token校验不过
            throw new AccessDeniedException(AccessDeniedException.Type.OVERTIME);
        }

        String loginId = (String) jwt.getPayload(AuthConstants.ACCESS_TOKEN_PAY_LOAD);

        Optional<AccountInfo> optionalAccountInfo = accountManager.getAccountInfo(loginId);

        AccountInfo accountInfo
                = optionalAccountInfo.orElseThrow(() -> new AccessDeniedException(AccessDeniedException.Type.OVERTIME));

        currentContext.addZuulRequestHeader(AuthConstants.HEADER_LOGIN_ID,loginId);

        return null;
    }

    /**
     * 是否允许匿名访问
     *
     * @param url
     * @return
     */
    private boolean isAllowAnon(String url) {

        List<String> anons = anonymousConfig.getAnon();
        if (CollUtil.isEmpty(anons)) {
            return false;
        }
        for (String anon : anons) {
            boolean match = antPathMatcher.match(anon, url);
            if (match) {
                return true;
            }
        }
        return false;
    }


}
