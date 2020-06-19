package com.example.newdemo.activity;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.activity
 * 文件名： WelcomeActivity
 * 创建者：hanhehuann
 * 创建时间：2020-06-16 9:31
 * 描述：TODO
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.newdemo.R;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
//延迟3秒
//跳转页面
public class WelcomeActivity extends BaseActivity{

    private Timer mTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    private void init() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("WelcomeActivity","当前线程是："+Thread.currentThread());
                toLogin();
            }
        },3000);
    }

    private void toLogin(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
    private void toMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
