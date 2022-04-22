package com.haozi.account.controller;

import com.haozi.account.service.IUsersService;
import com.haozi.common.model.ResponseResult;
import com.haozi.common.model.dto.user.AddUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author haozi
 * @since 2022-04-19
 */
@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    IUsersService usersService;

    @PostMapping("add")
    public ResponseResult<String> addUser(@RequestBody @Validated AddUserDTO addUserDTO){

        usersService.addUser(addUserDTO.getUserName());


        return null;
    }
}
