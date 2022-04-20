package com.haozi.auth.authentication;

import com.haozi.common.model.dto.auth.AccountInfo;

/** 认证管理器
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 10:56 下午
 */
public interface IAuthenticationManager {

    /**
     * 认证
     * @param principal
     * @param pwd
     * @param loginType
     */
     AccountInfo auth(String principal, String pwd, String loginType);
}
