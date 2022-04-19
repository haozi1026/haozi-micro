package com.haozi.auth.handler;

import cn.hutool.core.collection.CollUtil;
import com.haozi.auth.handler.fail.LoginFailProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 6:13 上午
 */
@Component
public class FailLoginHandler {

    @Autowired
    List<LoginFailProvider> loginFailProviders;

    /**
     * 登录失败处理
     * @param request
     * @param principal
     */
    public void handler(ServletRequest request,String principal){
        if(CollUtil.isEmpty(loginFailProviders)){
            return;
        }

        for (LoginFailProvider loginFailProvider : loginFailProviders) {
            loginFailProvider.handler((HttpServletRequest) request,principal);
        }

    }


}
