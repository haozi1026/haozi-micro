package com.haozi.gw.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/18 7:47 下午
 */
public class EncoderFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext.getCurrentContext().getResponse()
                .setHeader("Content-Type", "text/html;charset=utf-8");

        RequestContext.getCurrentContext().getResponse().setCharacterEncoding("UTF-8");
        return null;
    }
}
