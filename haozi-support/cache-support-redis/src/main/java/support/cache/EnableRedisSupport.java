package support.cache;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 10:27 上午
 */
@Import(RedisSupportSelector.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableRedisSupport {
}
