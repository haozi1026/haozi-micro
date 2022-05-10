package com.haozi.account.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/5/10 11:16 下午
 */
@Data
public class ResourcesBindRoleDTO {

    /**
     * 角色id
     */
    @NotEmpty(message = "角色id不能为空")
    Long roleId;
    /**
     * 资源id
     */
    @NotEmpty(message = "资源id不能为空")
    List<Long> resourceId;

}
