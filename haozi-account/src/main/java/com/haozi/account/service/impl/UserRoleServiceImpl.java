package com.haozi.account.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.haozi.account.dao.po.UserRole;
import com.haozi.account.dao.mapper.UserRoleMapper;
import com.haozi.account.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haozi.common.exception.internal.ParamMissingException;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public List<Integer> findRoleIdByUserName(String userName) {

        if(StrUtil.isBlank(userName)){
            throw new ParamMissingException("userName","根据userName查询角色列表");
        }

        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(UserRole::getRoleId);
        queryWrapper.eq(UserRole::getUsername,userName);

        List<UserRole> userRoles = this.baseMapper.selectList(queryWrapper);

        List<Integer> roleids = userRoles.stream().map(role -> role.getRoleId()).collect(Collectors.toList());

        return roleids;
    }

}
