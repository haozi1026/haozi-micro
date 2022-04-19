package com.haozi.gw.filter;

import com.haozi.common.exception.biz.AccessDeniedException;
import com.haozi.common.model.ResponseResult;
import com.haozi.common.util.JackSonUtil;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_ERROR_FILTER_ORDER;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/19 1:58 下午
 */
public class ErrorFilter extends SendErrorFilter {

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        SendErrorFilter.ExceptionHolder exception = this.findZuulException(ctx.getThrowable());


        if(exception instanceof AccessDeniedException){
            handlerAccessDenied((AccessDeniedException)exception);
        }
        return null;
    }

    private void handlerAccessDenied(AccessDeniedException ex){

        ResponseResult<String> fail = ResponseResult.fail(ex.toString());
        String s = JackSonUtil.toJson(fail);
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse();

        try {
            response.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

