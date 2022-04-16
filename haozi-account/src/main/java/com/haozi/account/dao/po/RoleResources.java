package com.haozi.account.dao.po;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-04-16
 */
@Getter
@Setter
@TableName("role_resources")
public class RoleResources implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer roleId;

      private Integer resourceId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
