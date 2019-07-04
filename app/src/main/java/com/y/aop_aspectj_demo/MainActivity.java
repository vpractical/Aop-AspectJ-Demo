package com.y.aop_aspectj_demo;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.y.aop_aspectj_demo.annotation.BehaviorTrace;
import com.y.aop_aspectj_demo.annotation.MethodDuration;
import com.y.aop_aspectj_demo.annotation.NeedLogin;

public class MainActivity extends AppCompatActivity {

    public static Context applicationContext;
    public static boolean isLogin = false;

    public static void show(String str){
        Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applicationContext = getApplicationContext();
        findViewById(R.id.btn_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnC();
            }
        });
    }

    @BehaviorTrace(value = "功能A")
    @MethodDuration
    public void btnA(View view) {
        SystemClock.sleep(100);
    }

    @NeedLogin
    @MethodDuration
    public void btnB(View view) {
        SystemClock.sleep(500);
        show("btnB");
    }

    @NeedLogin
    public void btnC() {
        show("btnC");
    }

    public void btnLogin(View view) {
        isLogin = !isLogin;
        show(isLogin ? "已登录" : "已退出");
    }
}
