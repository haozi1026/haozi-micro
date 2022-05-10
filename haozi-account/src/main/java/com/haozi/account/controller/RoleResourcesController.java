package com.haozi.account.controller;

import com.haozi.account.model.dto.ResourcesBindRoleDTO;
import com.haozi.account.service.IRoleResourcesService;
import com.haozi.common.model.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/5/10 11:15 下午
 */
@Controller
@RequestMapping("/role_resources")
@Slf4j
public class RoleResourcesController {

    @Autowired
    IRoleResourcesService roleResourcesService;

    @RequestMapping("/bind")
    public ResponseResult<String> bind(@RequestBody @Validated ResourcesBindRoleDTO resourcesBindRole){
        roleResourcesService.bindRoleResources(resourcesBindRole.getRoleId(), resourcesBindRole.getResourceId());
        return ResponseResult.success("操作c成功");
    }

}
