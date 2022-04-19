package com.haozi.security.aspect;

import com.haozi.security.anon.hasPermission;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/19 5:13 下午
 */
@Aspect
@Component
public class PermissionAspect {

    @Pointcut("@annotation(com.haozi.security.anon.hasPermission)")
    public void annonPoint(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        hasPermission annotation = method.getAnnotation(hasPermission.class);
        String resourceFlag = annotation.resourceFlag();



    }
}
