package com.haozi.auth.handler.fail;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录失败后处理器
 * @author zyh
 * @version 1.0
 * @date 2022/4/19 4:51 下午
 */
public interface LoginFailProvider {

    /**
     * 处理
     * @param request 请求头
     * @param principal 登录主体
     */
    void handler(HttpServletRequest request, String principal);
}
