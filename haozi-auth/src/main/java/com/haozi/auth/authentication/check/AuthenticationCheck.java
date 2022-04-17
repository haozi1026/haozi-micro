package com.haozi.auth.authentication.check;

import com.haozi.auth.authentication.token.BaseToken;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 6:58 上午
 */
public interface AuthenticationCheck {

    /**
     * 检查
     * @param baseToken
     */
    void check(BaseToken baseToken);
}
