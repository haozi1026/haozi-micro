package com.haozi.gw.filter;

import cn.hutool.core.util.ReUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import java.util.regex.Pattern;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/18 7:43 下午
 */
public class AuthFilter extends ZuulFilter {

    Pattern pattern = null;
    @Autowired
    ZuulProperties zuulProperties;

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

        String header = currentContext.getRequest().getHeader("ACCESS-TOKEN");
        String header1 = currentContext.getRequest().getHeader("REFRESH-TOKEN");

        return null;
    }
}
