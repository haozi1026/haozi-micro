package com.haozi.common.model.dto.auth;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 账号信息
 *
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 12:24 下午
 */
@Data
public class AccountInfo implements Serializable {


    private static final long serialVersionUID = -367221809088185471L;

    public AccountInfo(String principal, List<String> permission,List<String> roleName, String pwd, byte enable) {
        this.principal = principal;
        this.permission = permission;
        this.pwd = pwd;
        this.enable = enable;
        this.roleName = roleName;
    }
    public AccountInfo(){};
    /**
     * 用户名（账户主体）
     */
    private String principal;
    /**
     * 权限
     */
    private List<String> permission;
    /**
     * 角色
     */
    private List<String> roleName;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 是否可用
     */
    private byte enable;
    /**
     * 登录方式
     */
    private String loginType;

}
