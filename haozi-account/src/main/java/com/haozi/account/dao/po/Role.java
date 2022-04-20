package com.haozi.account.dao.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author haozi
 * @since 2022-04-16
 */
@Getter
@Setter
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer roleId;

    /**
     * 角色名
     */
    private String roleName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
