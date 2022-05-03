package com.haozi.security.aspect;

import cn.hutool.core.collection.CollUtil;
import com.haozi.account.manager.AccountManager;
import com.haozi.common.exception.biz.AccessDeniedException;
import com.haozi.common.model.dto.auth.AccountInfo;
import com.haozi.security.anon.hasPermission;
import com.haozi.security.anon.hasRole;
import com.haozi.security.context.SecurityContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/5/3 2:45 下午
 */
public class RoleAspect {

    @Autowired
    AccountManager accountManager;
    @Autowired
    SecurityContextHolder securityContextHolder;

    @Pointcut("@annotation(com.haozi.security.anon.hasRole)")
    public void pointcout() {

    }

    @Before("pointcout()")
    public void execute(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        hasRole annotation = method.getAnnotation(hasRole.class);
        String role = annotation.value();

        //取当前登录账户信息
        AccountInfo context = securityContextHolder.getContext();
        if (context == null) {
            throw new AccessDeniedException(AccessDeniedException.Type.NOT_LOGIN);
        }


        List<String> roleNames = context.getRoleName();

        if (CollUtil.isEmpty(roleNames)) {
            throw new AccessDeniedException(AccessDeniedException.Type.UNAUTHORIZED);
        }


        boolean hasRole = roleNames.stream().anyMatch(roleName -> roleName.equals(role));

        if(hasRole){
            return;
        }
        throw new AccessDeniedException(AccessDeniedException.Type.UNAUTHORIZED);
    }
}
