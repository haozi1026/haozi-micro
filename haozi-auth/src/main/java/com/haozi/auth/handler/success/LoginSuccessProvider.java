package com.haozi.auth.handler.success;

import com.haozi.common.model.dto.auth.AccountInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录成功后处理器
 * @author zyh
 * @version 1.0
 * @date 2022/4/19 4:43 下午
 */
public interface LoginSuccessProvider {

    /**
     * 处理
     * @param request
     * @param accountInfo
     */
    void handler(HttpServletRequest request, AccountInfo accountInfo);

}
