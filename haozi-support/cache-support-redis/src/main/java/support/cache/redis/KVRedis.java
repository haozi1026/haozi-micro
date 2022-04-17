package support.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import support.cache.KVCache;

import java.util.Optional;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 12:56 下午
 */
public class KVRedis<T> implements KVCache<T> {


    RedisTemplate<String,T> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void cache(String key, T v, long seconds) {
        redisTemplate.opsForValue().set(key,v,seconds);
    }

    @Override
    public Optional<T> get(String key) {
        T t = redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(t);
    }
}
