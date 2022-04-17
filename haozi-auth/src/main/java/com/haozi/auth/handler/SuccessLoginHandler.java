package com.haozi.auth.handler;

import cn.hutool.core.util.StrUtil;
import com.haozi.common.exception.ParamMissingException;
import com.haozi.common.model.dto.account.AccountInfo;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 6:37 上午
 */
@Component
public class SuccessLoginHandler {

    /**
     * 登录成功处理
     *
     * @param request
     */
    public void handler(ServletRequest request, String principal) {

    }

}
