package com.haozi.account.service;

import com.haozi.account.dao.po.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author haozi
 * @since 2022-04-16
 */
public interface IRoleService extends IService<Role> {

    /**
     * 查询角色对象
     * @param ids
     * @return
     */
    List<Role> findRoleByIds(List<Integer> ids);

}
