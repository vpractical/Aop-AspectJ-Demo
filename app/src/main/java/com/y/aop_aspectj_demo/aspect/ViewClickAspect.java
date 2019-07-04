package com.y.aop_aspectj_demo.aspect;

import android.util.Log;
import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ViewClickAspect {

    @Around("execution(* android.view.View.OnClickListener.onClick(..))")
    public void handleJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        View view = null;
        for (Object arg : args) {
            if (arg instanceof View) {
                view = (View) arg;
            }
        }
        //获取id
        String resEntryName = null;
        String resName = null;
        if (view != null) {
            resEntryName = view.getContext().getResources().getResourceEntryName(view.getId());
            resName = view.getContext().getResources().getResourceName(view.getId());
        }
        joinPoint.proceed();
        //ViewClickAspect: resEntryName: btn_c;  resName: com.y.aop_aspectj_demo:id/btn_c
        Log.e("ViewClickAspect", "resEntryName: " + resEntryName + ";  resName: " + resName);
    }

}
