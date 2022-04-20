package com.haozi.auth.remote;

import com.haozi.common.model.ResponseResult;
import com.haozi.common.model.dto.auth.AccountInfo;
import com.haozi.common.model.dto.auth.EmailRequest;
import com.haozi.common.model.dto.auth.PhoneRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 5:14 下午
 */
@FeignClient("ACCOUNT")
public interface AccountClient {

    /**
     * 通过邮箱获取账户信息
     * @param emailRequest
     * @return
     */
    @PostMapping(value = "/account/email")
    ResponseResult<AccountInfo> getAccountByEmail(@RequestBody EmailRequest emailRequest);

    /**
     * 通过手机号获取账户信息
     * @param emailRequest
     * @return
     */
    @PostMapping(value = "/account/phone")
    ResponseResult<AccountInfo> getAccountByPhone(@RequestBody PhoneRequest emailRequest);
}
