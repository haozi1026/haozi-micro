package com.haozi.auth.authentication.provider;

import com.haozi.auth.authentication.token.BaseToken;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 10:30 下午
 */
public interface AuthProvider {

    /**
     * 登录类型
     * @param loginType
     * @return
     */
    boolean support(String loginType);

    /**
     * 认证
     * @param principal
     * @param pwd
     */
    BaseToken auth(String principal, String pwd);

}
