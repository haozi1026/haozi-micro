package com.haozi.account.service.impl;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LFUCache;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.haozi.account.dao.po.Resouces;
import com.haozi.account.dao.po.RoleResources;
import com.haozi.account.dao.mapper.RoleResourcesMapper;
import com.haozi.account.service.IResoucesService;
import com.haozi.account.service.IRoleResourcesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class RoleResourcesServiceImpl extends ServiceImpl<RoleResourcesMapper, RoleResources> implements IRoleResourcesService {

    LFUCache<Integer,List<Resouces>> roleResourcesCache
            = CacheUtil.newLFUCache(15);

    @Autowired
    IResoucesService resoucesService;

    @Override
    public List<Resouces> findResourcesByRoleId(List<Integer> roleids) {

        List<Resouces> resoucesList = new ArrayList<>();

        for (Integer roleid : roleids) {
            List<Resouces> resourcesByRoleId = findResourcesByRoleId(roleid);
            if(CollUtil.isNotEmpty(resourcesByRoleId)){
                resoucesList.addAll(resourcesByRoleId);
            }
        }
        return resoucesList;
    }

    @Override
    public List<Resouces> findResourcesByRoleId(Integer roleids) {

        List<Resouces> resouces = roleResourcesCache.get(roleids);

        if(resouces != null){
            return resouces;
        }

        LambdaQueryWrapper<RoleResources>
                wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(RoleResources::getRoleId,roleids);

        List<RoleResources> roleResources = this.baseMapper.selectList(wrapper);

        List<Long> resourceId = roleResources.stream().map(RoleResources::getResourceId).collect(Collectors.toList());

        List<Resouces> resourcesById = resoucesService.findResourcesById(resourceId);

        roleResourcesCache.put(roleids,resourcesById);

        return resourcesById;
    }
}
