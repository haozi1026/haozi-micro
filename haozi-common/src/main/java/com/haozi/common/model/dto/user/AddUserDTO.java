package com.haozi.common.model.dto.user;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;


/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/20 4:02 下午
 */
@Data
public class AddUserDTO {

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    private String userName;

}
