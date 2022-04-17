package support.cache;

import com.haozi.common.model.dto.account.AccountInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import support.cache.account.AccountInfoCache;
import support.cache.redis.AccountInfoRedis;
import support.cache.redis.KVRedis;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 11:42 上午
 */
@Configuration
public class RedisSupportAutoConfig {

    @Bean
    public AccountInfoCache accountInfoCache(){
        return new AccountInfoRedis();
    }

    @Bean
    public KVRedis kvRedis(){
        return new KVRedis();
    }
}
