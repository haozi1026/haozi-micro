package com.haozi.account.dao.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author haozi
 * @since 2022-04-19
 */
@Getter
@Setter
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
      private String username;

    private String pwd;

    /**
     * 是否可用
     */
    private byte enabled;

    private String email;

    /**
     * 手机号
     */
    private String phoneNumber;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
