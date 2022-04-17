package com.haozi.auth.remote;

import com.haozi.common.model.ResponseResult;
import com.haozi.common.model.dto.account.AccountInfo;
import com.haozi.common.model.dto.account.EmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 5:14 下午
 */
@FeignClient("account")
public interface AccountClient {

    @RequestMapping(method = RequestMethod.POST, value = "/account/email", consumes = "application/json")
    ResponseResult<AccountInfo> getAccountByEmail(EmailRequest emailRequest);

}
