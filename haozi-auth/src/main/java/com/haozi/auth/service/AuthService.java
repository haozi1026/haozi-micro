package com.haozi.auth.service;

import com.haozi.common.model.dto.auth.AccountInfo;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 10:27 下午
 */
public interface AuthService {

    /**
     * 认证
     * @param principal 主体
     * @param pwd 密码
     * @param loginType 登录类型
     * @return
     */
    AccountInfo auth(String principal, String pwd, String loginType);

}
