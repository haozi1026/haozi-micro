package com.haozi.account.model.dto;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/23 10:57 上午
 */
@Data
public class ResourcesRequestAddDTO {
    /**
     * 资源名
     */
    @NotBlank(message = "资源名不能为空")
    private String resourceName;

    /**
     * 父级id
     */
    private Integer parentId;
    /**
     * 菜单类型 类型0:按钮 1：菜单
     */
    @NotNull(message = "菜单类型不能为空")
    private Integer type;

    /**
     * 请求地址
     */
    private String url;
    /**
     * 资源标识
     */
    private String resourceFlag;
}
