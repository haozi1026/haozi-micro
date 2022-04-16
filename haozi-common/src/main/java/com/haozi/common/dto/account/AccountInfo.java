package com.haozi.common.dto.account;

import lombok.Data;

import java.util.List;

/**
 * 账号信息
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 12:24 下午
 */
@Data
public class AccountInfo {

    /**
     * 用户名（账户主体）
     */
    private String userName;
    /**
     * 权限
      */
    private List<String> permission;

}
