package com.haozi.auth.dao.po;

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
 * @since 2022-04-17
 */
@Getter
@Setter
@TableName("token_config")
public class TokenConfig implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

    private String accessTokenKey;

    private String refreshTokenKey;

    /**
     * 持续时间分钟
     */
    private Integer accessTokenExp;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
