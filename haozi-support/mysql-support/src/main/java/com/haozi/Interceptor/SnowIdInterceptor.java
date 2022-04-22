package com.haozi.Interceptor;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

/**
 * 自动为主键赋值 雪花 id
 * @author zyh
 * @version 1.0
 * @date 2022/4/22 3:23 下午
 */
public class SnowIdInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }


}
