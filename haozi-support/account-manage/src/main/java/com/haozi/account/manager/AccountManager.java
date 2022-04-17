package com.haozi.account.manager;

import com.haozi.common.model.dto.account.AccountInfo;

import java.util.Optional;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 1:31 下午
 */
public interface AccountManager {
    /**
     * 存账号信息
     * @param accountInfo
     */
     void saveAccountinfo(String loginId,AccountInfo accountInfo,long minute);

    /**
     * 取账号信息
     * @param loginId
     * @return
     */
    Optional<AccountInfo>  getAccountInfo(String loginId);
}
