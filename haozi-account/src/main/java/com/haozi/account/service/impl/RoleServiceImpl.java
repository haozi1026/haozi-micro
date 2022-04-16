package com.haozi.account.service.impl;

import com.haozi.account.dao.po.Role;
import com.haozi.account.dao.mapper.RoleMapper;
import com.haozi.account.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
