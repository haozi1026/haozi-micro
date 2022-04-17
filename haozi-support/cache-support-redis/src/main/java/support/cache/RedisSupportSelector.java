package support.cache;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import support.cache.redis.AccountInfoRedis;
import support.cache.redis.KVRedis;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 10:22 上午
 */
public class RedisSupportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

       String[] clazz =  new String[]{
               RedisSupportAutoConfig.class.getName()
        };
       return clazz;

    }
}
