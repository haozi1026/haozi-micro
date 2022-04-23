package com.haozi.Interceptor;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LRUCache;
import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import com.haozi.annotation.SnowId;
import com.haozi.common.exception.internal.InternalException;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动为主键赋值 雪花 id
 *
 * @author zyh
 * @version 1.0
 * @date 2022/4/22 3:23 下午
 */
@Intercepts(
        {
                @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        }
)
public class SnowIdInterceptor implements Interceptor {

    LRUCache<Class, List<Field>>
            cacheField = CacheUtil.newLRUCache(60);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        //取入参
        Object[] args = invocation.getArgs();
        if (args.length < 2) {
            return continueExcute(invocation);
        }
        Object obj = args[1];

        if (obj == null || ObjectUtil.isBasicType(obj)) {
            return continueExcute(invocation);
        }
        List<Field> fields = cacheField.get(obj.getClass());

        if (fields == null) {
            fields = getTargetField(obj);
            cacheField.put(obj.getClass(), fields);
        }

        assignment(fields, obj);
        return continueExcute(invocation);
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 赋值
     */
    private void assignment(List<Field> fields, Object obj) {

        if (CollUtil.isEmpty(fields)) {
            return;
        }

        for (Field field : fields) {
            Snowflake snowflake = IdUtil.getSnowflake();
            long id = snowflake.nextId();
            ReflectUtil.setFieldValue(obj, field, id);
        }

    }

    /**
     * 获取需要赋值的field
     *
     * @return
     */
    private List<Field> getTargetField(Object obj) {
        //获取所有字段
        Field[] fields = ReflectUtil.getFields(obj.getClass());
        List<Field> targetField = new ArrayList<>();
        for (Field field : fields) {

            SnowId annotation = AnnotationUtil.getAnnotation(field, SnowId.class);
            if (annotation != null) {
                targetField.add(field);
            }
        }
        return targetField;
    }

    /**
     * 继续执行
     *
     * @param invocation
     * @return
     */
    private Object continueExcute(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        Method method = invocation.getMethod();
        Object target = invocation.getTarget();
        Object[] args = invocation.getArgs();


        Object invoke = method.invoke(target, args);
        return invoke;

    }

}
