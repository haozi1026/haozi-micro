package support.cache.redis;

import com.haozi.common.model.dto.account.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import support.cache.account.AccountInfoCache;

import java.util.Optional;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 1:26 下午
 */
@Component
public class AccountInfoRedis extends AccountInfoCache {

    @Autowired
    RedisTemplate<String,AccountInfo> redisTemplate;

    @Override
    public void cache(String key, AccountInfo v, long seconds) {
        redisTemplate.opsForValue().set(key,v,seconds);
    }

    @Override
    public Optional<AccountInfo> get(String key) {
        AccountInfo accountInfo = redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(accountInfo);
    }

}
