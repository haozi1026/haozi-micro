package com.haozi.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.haozi.account.dao.po.Role;
import com.haozi.account.dao.mapper.RoleMapper;
import com.haozi.account.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author haozi
 * @since 2022-04-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {



    @Override
    public List<Role> findRoleByIds(List<Integer> ids) {

        LambdaQueryWrapper<Role>
                queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.in(Role::getRoleId,ids);

        List<Role> roles = this.baseMapper.selectList(queryWrapper);

        return roles;
    }
}
