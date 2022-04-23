package com.haozi.account.service.impl;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LFUCache;
import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.haozi.account.dao.po.Resouces;
import com.haozi.account.dao.mapper.ResoucesMapper;
import com.haozi.account.model.dto.ResourcesRequestDTO;
import com.haozi.account.service.IResoucesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author haozi
 * @since 2022-04-16
 */
@Service
public class ResoucesServiceImpl extends ServiceImpl<ResoucesMapper, Resouces> implements IResoucesService {

    LFUCache<Integer, Resouces> roleResourcesCache
            = CacheUtil.newLFUCache(200);

    @Override
    public Resouces findResourcesById(Integer resourceId) {

        Resouces resoucesCache = roleResourcesCache.get(resourceId);

        if(resoucesCache!=null){
            return resoucesCache;
        }
        Resouces resoucesRes = this.baseMapper.selectById(resourceId);
        roleResourcesCache.put(resourceId,resoucesRes);
        return resoucesRes;
    }



    @Override
    public List<Resouces> findResourcesById(List<Integer> resourceId) {

        List<Resouces> resoucesList = new ArrayList<>();
        List<Integer> needQuery = new ArrayList<>();
        for (Integer id : resourceId) {

            Resouces resouces = roleResourcesCache.get(id);
            if(resouces!=null){
                resoucesList.add(resouces);
                continue;
            }
            needQuery.add(id);
        }
        LambdaQueryWrapper<Resouces>
                queryWrapper = new LambdaQueryWrapper();

        queryWrapper.in(Resouces::getResourcesId,needQuery);

        List<Resouces> resouces = this.baseMapper.selectList(queryWrapper);

        for (Resouces resouce : resouces) {
            roleResourcesCache.put(resouce.getResourcesId(),resouce);
            resoucesList.add(resouce);
        }

        return resoucesList;
    }

    @Override
    public Resouces add(ResourcesRequestDTO resoucesDTO) {

        Resouces resouces = new Resouces();
        BeanUtils.copyProperties(resoucesDTO,resouces);
        resouces.setCreateTime(LocalDateTime.now());
        resouces.setUpdateTime(LocalDateTime.now());
        this.baseMapper.insert(resouces);

        return resouces;
    }

}
