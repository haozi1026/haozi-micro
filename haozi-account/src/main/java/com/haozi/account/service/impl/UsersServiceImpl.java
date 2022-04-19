package com.haozi.account.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.haozi.account.dao.po.Resouces;
import com.haozi.account.dao.po.Users;
import com.haozi.account.dao.mapper.UsersMapper;
import com.haozi.account.service.IRoleResourcesService;
import com.haozi.account.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haozi.common.exception.internal.ParamMissingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author haozi
 * @since 2022-04-16
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Autowired
    IRoleResourcesService roleResourcesService;

    @Override
    public Users selectByEmail(String email) {

        if(StrUtil.isBlank(email)){
            throw new ParamMissingException("email","根据email查账户信息");
        }

        LambdaQueryWrapper<Users>
                queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Users::getEmail,email);

        Users users = this.baseMapper.selectOne(queryWrapper);
        return users;
    }

    @Override
    public Users findByUserName(String userName) {

        if (StrUtil.isBlank(userName)) {
            throw new ParamMissingException("userName", "根据用户名查库");
        }
        return this.baseMapper.selectById(userName);
    }

    @Override
    public List<String> resourcesFlag(List<Integer> roleIds) {
        List<Resouces> resourcesByRoleId = roleResourcesService.findResourcesByRoleId(roleIds);

        List<String> resourcesFlag = resourcesByRoleId.stream().map(Resouces::getResourceFlag).collect(Collectors.toList());

        return resourcesFlag;
    }
}
