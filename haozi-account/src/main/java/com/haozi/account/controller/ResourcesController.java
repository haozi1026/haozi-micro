package com.haozi.account.controller;

import com.haozi.account.model.dto.ResourcesRequestDTO;
import com.haozi.account.service.IResoucesService;
import com.haozi.common.model.ResponseResult;
import com.haozi.security.anon.hasPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/23 10:36 上午
 */
@RestController
@RequestMapping("/resources")
public class ResourcesController {

    @Autowired
    IResoucesService resoucesService;

    /**
     * 新增资源
     * @return
     */
    @PostMapping("/add")
    @hasPermission("resources.add")
    public ResponseResult<String> add(@RequestBody  @Validated ResourcesRequestDTO resourcesRequest){
        resoucesService.add(resourcesRequest);
        return ResponseResult.success("新增成功");
    }


}
