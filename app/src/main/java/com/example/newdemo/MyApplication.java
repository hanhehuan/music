package com.example.newdemo;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo
 * 文件名： MyApplication
 * 创建者：hanhehuann
 * 创建时间：2020-06-16 9:25
 * 描述：TODO
 */

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import io.realm.Realm;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
        Realm.init(this);
    }
}
