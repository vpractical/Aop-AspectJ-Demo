package com.y.aop_aspectj_demo.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class MethodDurationAspect {
    /**
     * 那些注解的地方放到当前切面进行处理
     * execution表示JPoint是执行方法的地方，AspectJ会对被执行方法做处理。而call表示JPoint是调用方法的地方，AspectJ会对调用处做处理
     */
    @Pointcut("execution(@com.y.aop_aspectj_demo.annotation.MethodDuration *  *(..))")
    public void methodAnnotatedWithMethodDuration() {

    }

    /**
     * 对进入切面的内容如何处理
     * before/after时 参数joinPoint为null
     */
    @Around("methodAnnotatedWithMethodDuration()")
    public Object handleJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - begin;
        Log.e("MethodDurationAspect", String.format("%s类的%s方法执行耗时%dms",
                className, methodName, duration));
        return result;
    }



    @Before("execution(* android.view.View.OnClickListener.onClick(..))")
    public void p(ProceedingJoinPoint joinPoint){
        Log.e("MethodDurationAspect","所有View.OnClickListener.onClick");
    }
}
