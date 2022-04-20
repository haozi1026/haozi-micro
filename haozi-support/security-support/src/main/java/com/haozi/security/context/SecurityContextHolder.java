package com.haozi.security.context;

import com.haozi.common.model.dto.account.AccountInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/20 1:11 下午
 */
@Component
public class SecurityContextHolder {


    /**
     * 存上下文的
     */
    private static final ThreadLocal<AccountInfo> contextHolder = new ThreadLocal<>();

    /**
     * 清理
     */
    public void clearContext() {
        contextHolder.remove();
    }

    /**
     * 获取上下文
     *
     * @return
     */
    public AccountInfo getContext() {
        AccountInfo ctx = contextHolder.get();
        return ctx;
    }
    /**
     * 设置上下文
     *
     * @param context
     */
    public void setContext(AccountInfo context) {
        contextHolder.set(context);
    }
}
