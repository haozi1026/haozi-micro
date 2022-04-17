package com.haozi.auth.service.impl;

import cn.hutool.cache.impl.LRUCache;
import com.haozi.auth.dao.po.TokenConfig;
import com.haozi.auth.dao.mapper.TokenConfigMapper;
import com.haozi.auth.service.ITokenConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author haozi
 * @since 2022-04-17
 */
@Service
public class TokenConfigServiceImpl extends ServiceImpl<TokenConfigMapper, TokenConfig> implements ITokenConfigService {

    LRUCache<String,TokenConfig>
        tokenConfigLRUCache = new LRUCache<>(1);

    @Override
    public TokenConfig tokenConfig() {

        if(tokenConfigLRUCache.containsKey(cacheKey)){
            return tokenConfigLRUCache.get(cacheKey);
        }

        TokenConfig tokenConfig = this.baseMapper.selectOne(null);
        tokenConfigLRUCache.put(cacheKey,tokenConfig);

        return tokenConfig;
    }

    private final String cacheKey = "cache-token-config";
}
