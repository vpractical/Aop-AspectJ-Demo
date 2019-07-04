package com.y.aop_aspectj_demo.aspect;

import android.util.Log;

import com.y.aop_aspectj_demo.MainActivity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class NeedLoginAspect {

    @Around("execution(@com.y.aop_aspectj_demo.annotation.NeedLogin *  *(..))")
    public void handleJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        if(MainActivity.isLogin){
            joinPoint.proceed();
        }else{
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            String className = methodSignature.getDeclaringType().getSimpleName();
            String methodName = methodSignature.getName();
            Log.e("NeedLoginAspect", String.format("%s类的%s方法需要登录后使用",
                    className, methodName));
            MainActivity.show("请登录后使用");
        }

    }

}
