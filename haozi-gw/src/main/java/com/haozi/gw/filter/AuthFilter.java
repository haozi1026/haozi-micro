package com.haozi.gw.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.AntPathMatcher;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.haozi.common.model.ResponseResult;
import com.haozi.common.util.jackSonUtil;
import com.haozi.gw.config.AnonymousConfig;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
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
        HttpServletResponse response = currentContext.getResponse();
        StringBuffer requestURL = currentContext.getRequest().getRequestURL();
        //获取目标地址
        String requestUrl = ReUtil.get(pattern, requestURL.toString(), 1);

        if(isAllowAnon(requestUrl)){
            return null;
        }
        //取访问 token
        String accessToken = currentContext.getRequest().getHeader("ACCESS-TOKEN");

        if(StrUtil.isBlank(accessToken)){
            //返回
            accessForbidden(response);
            return false;
        }
        //token 校验
        try {
            JWTValidator.of(accessToken).validateDate();
        } catch (Exception ex){
            // token校验不过
            //返回
            accessForbidden(response);
            return false;
        }
        return null;
    }

    /**
     * 禁止访问
     */
    private void accessForbidden(HttpServletResponse response){

        ResponseResult<String> responseResult = ResponseResult.fail("未登录用户禁止访问");
        String res = jackSonUtil.toJson(responseResult);

        responseResult.setCode(HttpStatus.HTTP_FORBIDDEN);
        try {
            response.getWriter().write(res);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    /**
     * 是否允许匿名访问
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
            if(match){
                return true;
            }
        }
        return false;
    }
}
