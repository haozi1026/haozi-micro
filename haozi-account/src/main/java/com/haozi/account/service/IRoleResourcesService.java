package com.haozi.account.service;

import com.haozi.account.dao.po.Resouces;
import com.haozi.account.dao.po.RoleResources;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author haozi
 * @since 2022-04-16
 */
public interface IRoleResourcesService extends IService<RoleResources> {

    /**
     * 角色id 获取资源
     * @param roleids
     * @return
     */
    List<Resouces> findResourcesByRoleId(List<Integer> roleids);

    /**
     * 角色id 获取资源
     * @param roleids
     * @return
     */
    List<Resouces> findResourcesByRoleId(Integer roleids);

}
