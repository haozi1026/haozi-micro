package com.haozi.account.service;

import com.haozi.account.dao.po.Resouces;
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
public interface IResoucesService extends IService<Resouces> {

    /**
     * 通过资源名获取资源id
     * @param resourceId
     * @return
     */
    Resouces findResourcesById(Integer resourceId);

    /**
     * 通过资源名获取资源id
     * @param resourceId
     * @return
     */
    List<Resouces> findResourcesById(List<Integer> resourceId);


}
