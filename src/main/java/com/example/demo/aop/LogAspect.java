package com.example.demo.aop;

import com.example.demo.annotation.AopLogs;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author GEEX177
 * @date 2019/10/24
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    private Gson gson = new Gson();

    @Pointcut("@within(com.example.demo.annotation.AopLogs)")
    public void logAspect() {

    }

    @Around("logAspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String className = point.getTarget().getClass().getName();
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        String value = "";
        AopLogs aopLogs = method.getAnnotation(AopLogs.class);
        if (aopLogs != null) {
            value = aopLogs.value();
        }
        Object[] args = point.getArgs();
        String params = gson.toJson(args);

        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        log.info("Class: " + className + ", Method: " + method.getName() + ", Value: " + value);
        log.info("params[]:" + params);
        log.info("process time:" + time);
        return result;
    }
}
