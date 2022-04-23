package com.haozi.auth.handler;

import cn.hutool.core.collection.CollUtil;
import com.haozi.auth.handler.success.LoginSuccessProvider;
import com.haozi.common.model.dto.auth.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 6:37 上午
 */
@Component
public class SuccessLoginHandler {

    @Autowired(required = false)
    List<LoginSuccessProvider> loginSuccessProvider;

    /**
     * 登录成功处理
     *
     * @param request
     */
    public void handler(ServletRequest request, AccountInfo accountInfo) {

        if (CollUtil.isEmpty(loginSuccessProvider)) {
            return;
        }

        for (LoginSuccessProvider successProvider : loginSuccessProvider) {
            successProvider.handler((HttpServletRequest) request, accountInfo);
        }
    }

}
