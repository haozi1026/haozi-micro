package com.haozi.account.service.impl;


import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LFUCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.haozi.account.dao.po.Resouces;
import com.haozi.account.dao.mapper.ResoucesMapper;
import com.haozi.account.model.dto.ResourcesRequestDTO;
import com.haozi.account.service.IResoucesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haozi.common.exception.internal.ParamMissingException;
import com.haozi.common.util.ValidationUtil;
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

    LFUCache<Long, Resouces> roleResourcesCache
            = CacheUtil.newLFUCache(200);



    @Override
    public Resouces findResourcesById(Long resourceId) {

        Resouces resoucesCache = roleResourcesCache.get(resourceId);

        if(resoucesCache!=null){
            return resoucesCache;
        }
        Resouces resoucesRes = this.baseMapper.selectById(resourceId);
        roleResourcesCache.put(resourceId,resoucesRes);
        return resoucesRes;
    }



    @Override
    public List<Resouces> findResourcesById(List<Long> resourceId) {

        List<Resouces> resoucesList = new ArrayList<>();
        List<Long> needQuery = new ArrayList<>();
        for (Long id : resourceId) {

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

        ParamMissingException illegalEx = ValidationUtil.verifyJsr303(resoucesDTO, "新增资源");

        if(illegalEx!=null){
            throw illegalEx;
        }


        Resouces resouces = new Resouces();
        BeanUtils.copyProperties(resoucesDTO,resouces);
        resouces.setCreateTime(LocalDateTime.now());
        resouces.setUpdateTime(LocalDateTime.now());
        this.baseMapper.insert(resouces);
        return resouces;
    }

}
