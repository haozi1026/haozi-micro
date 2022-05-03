package com.haozi.common.model.dto.auth;

import com.haozi.common.constants.LoginResult;
import com.haozi.common.model.dto.token.TokenInfo;
import lombok.Data;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 6:24 上午
 */
@Data
public class LoginResponse {

    /**
     * token
     */
    private TokenInfo tokenInfo;
    /**
     * 登录id
     */
    private String loginId;
    /**
     * 登录状态
     */
    private byte loginStatus;
    /**
     * 信息
     */
    private String message;

    /**
     * 失败
     * @return
     */
    public static LoginResponse fail() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setLoginStatus(LoginResult.FAIL.getCode());
        loginResponse.setMessage("登录失败,用户名或密码错误");
        return loginResponse;
    }

    /**
     * 成功
     * @param tokenInfo
     * @return
     */
    public static LoginResponse success(TokenInfo tokenInfo,String loginId) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setLoginStatus(LoginResult.SUCCESS.getCode());
        loginResponse.setTokenInfo(tokenInfo);
        loginResponse.setMessage("登录成功");
        loginResponse.setLoginId(loginId);
        return loginResponse;
    }
}
