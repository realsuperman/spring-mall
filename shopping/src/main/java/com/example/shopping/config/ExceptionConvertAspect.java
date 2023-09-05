package com.example.shopping.config;

import com.example.shopping.exception.DataAccessExceptionWithHttpServletRequest;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class ExceptionConvertAspect {
    @AfterThrowing(pointcut = "execution(* com.example.shopping.controller..*.*(..))", throwing = "ex")
    public void handleException(Throwable ex) {
        if (ex instanceof DataAccessException) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            throw new DataAccessExceptionWithHttpServletRequest(ex.getMessage(), ex, request.getHeader("X-Requested-With"));
        }
    }
}
