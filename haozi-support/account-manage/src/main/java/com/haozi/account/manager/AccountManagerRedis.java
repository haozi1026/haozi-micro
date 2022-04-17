package com.haozi.account.manager;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import com.haozi.common.model.dto.account.AccountInfo;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 1:33 下午
 */
@Service
public class AccountManagerRedis implements AccountManager {

    Jedis jedis = RedisDS.create().getJedis();

    @Override
    public void saveAccountinfo(String loginId, AccountInfo accountInfo,long minute) {

        byte[] bytes = ObjectUtil.serialize(accountInfo);
        String key = generateKey(loginId);
        SetParams setParams = new SetParams();
        setParams.exAt(minute*60);
        jedis.set(key.getBytes(StandardCharsets.UTF_8), bytes,setParams);
    }

    @Override
    public Optional<AccountInfo> getAccountInfo(String loginId) {
        String key = generateKey(loginId);
        byte[] bytes = jedis.get(key.getBytes(StandardCharsets.UTF_8));
        if (bytes == null || bytes.length < 1) {
            return Optional.empty();
        }
        return Optional.of(ObjectUtil.deserialize(bytes));
    }

    private String generateKey(String loginId) {
        return CACHE_KEY + loginId;
    }

    private final String CACHE_KEY = "account-cache";
}
