package com.haozi.account.dao.po;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author haozi
 * @since 2022-04-16
 */
@Getter
@Setter
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

      private String username;

    private String pwd;

    private byte enabled;

    private String email;


}
