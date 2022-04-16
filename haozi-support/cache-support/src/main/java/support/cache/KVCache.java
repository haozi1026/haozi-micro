package support.cache;

import java.util.Optional;

/**
 * k-v缓存
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 12:39 下午
 */
public interface KVCache<T> {


    /**
     * 缓存
     * @param key 键
     * @param v 值
     * @param seconds 过期时间
     */
     void cache(String key,T v,long seconds);

    /**
     * 获取
     * @param key 键
     * @return
     */
     Optional<T> get(String key);

}
