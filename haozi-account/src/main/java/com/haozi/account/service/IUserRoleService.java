package com.haozi.account.service;

import com.haozi.account.dao.po.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haozi.account.dao.po.Users;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author haozi
 * @since 2022-04-16
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 通过用户名查用户id
     * @param userName
     * @return
     */
    List<Integer> findRoleIdByUserName(String userName);


}
