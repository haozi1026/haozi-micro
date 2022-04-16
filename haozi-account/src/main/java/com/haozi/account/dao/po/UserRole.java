package com.haozi.account.dao.po;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

      private String username;

      private Integer roleId;


}
