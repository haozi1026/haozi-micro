package com.haozi.security.aspect;

import cn.hutool.core.util.StrUtil;
import com.haozi.account.manager.AccountManager;
import com.haozi.common.exception.biz.AccessDeniedException;
import com.haozi.common.model.dto.auth.AccountInfo;
import com.haozi.security.anon.hasPermission;
import com.haozi.security.context.SecurityContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.weaver.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;


/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/19 5:13 下午
 */
@Aspect
//@Component
@ConditionalOnClass(Advice.class)
public class PermissionAspect {

    @Autowired
    AccountManager accountManager;
    @Autowired
    SecurityContextHolder securityContextHolder;

    @Pointcut("@annotation(com.haozi.security.anon.hasPermission)")
    public void annonPoint(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        hasPermission annotation = method.getAnnotation(hasPermission.class);
        String resourceFlag = annotation.resourceFlag();

        if (StrUtil.isBlank(resourceFlag)) {
            return;
        }
        //取当前登录账户信息
        AccountInfo context = securityContextHolder.getContext();
        if (context == null) {
            throw new AccessDeniedException(AccessDeniedException.Type.NOT_LOGIN);
        }
        List<String> permissions = context.getPermission();

        for (String permission : permissions) {

            if(permission.equals(resourceFlag)){
                return;
            }
        }
        throw new AccessDeniedException(AccessDeniedException.Type.UNAUTHORIZED);
    }
}
