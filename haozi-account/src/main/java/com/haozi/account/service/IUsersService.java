package com.haozi.account.service;

import com.haozi.account.dao.po.Users;
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
public interface IUsersService extends IService<Users> {

    /**
     * 通过email获取账户信息
     * @param email
     * @return
     */
    Users selectByEmail(String email);

    /**
     * 通过手机获取账户信息
     * @param phone
     * @return
     */
    Users selectByPhone(String phone);
    /**
     * 通过userName 查找
     *
     * @param userName
     * @return
     */
    Users findByUserName(String userName);

    /**
     * 通过用户名查该用户的资源标识
     *
     * @param roleIds
     * @return
     */
    List<String> resourcesFlag(List<Integer> roleIds);

    /**
     * 添加用户
     * @param userName 用户名
     * @return
     */
    Users addUser(String userName);

}
