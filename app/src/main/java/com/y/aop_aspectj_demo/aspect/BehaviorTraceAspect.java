package com.y.aop_aspectj_demo.aspect;

import android.util.Log;

import com.y.aop_aspectj_demo.annotation.BehaviorTrace;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class BehaviorTraceAspect {

    @Around("execution(@com.y.aop_aspectj_demo.annotation.BehaviorTrace *  *(..))")
    public Object handleJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        String value = methodSignature.getMethod().getAnnotation(BehaviorTrace.class).value();
        Log.e("BehaviorTraceAspect", String.format("%s功能：%s类的%s方法执行了",
                value, className, methodName));
        return joinPoint.proceed();
    }

}
