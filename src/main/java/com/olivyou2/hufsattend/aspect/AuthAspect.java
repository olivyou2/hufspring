package com.olivyou2.hufsattend.aspect;

import com.olivyou2.hufsattend.exception.AuthorizationNotSuggestedException;
import com.olivyou2.hufsattend.exception.MalformedAuthorizationException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Component
@Aspect
public class AuthAspect {
    @Around("@annotation(com.olivyou2.hufsattend.annotation.Auth)")
    public Object authInfoProcess(ProceedingJoinPoint pjp) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String authorization = request.getHeader("Authorization");

        if (authorization == null) {
            throw new AuthorizationNotSuggestedException();
        }

        if (!authorization.contains(":") || authorization.indexOf(":") != authorization.lastIndexOf(":")){
            throw new MalformedAuthorizationException();
        }

        return pjp.proceed();
    }

}
