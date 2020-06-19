package com.example.newdemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.newdemo.R;
import com.example.newdemo.utils.OkHttpUtils;
import com.example.newdemo.utils.ResultData;
import com.example.newdemo.utils.ResultUtil;
import com.google.gson.Gson;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button btn;
    private OkHttpUtils okHttpUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(this);
        okHttpUtils = new OkHttpUtils(this, new OkHttpUtils.HttpCallBack() {
            @Override
            public void success(String msg) {
                Log.i(TAG,"请求成功！");
                ResultData resultData = new Gson().fromJson(msg,ResultData.class);
            }

            @Override
            public void failure(String e) {//400
                Log.i(TAG,"请求失败！");
                ResultData resultData = new Gson().fromJson(e,ResultData.class);
            }
        });
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG,"点击了按钮");
        okHttpUtils.getSyn("http://www.baidu.com");
    }

    @Override
    protected void onDestroy() {
        if (okHttpUtils != null){
            okHttpUtils.cancelHttp();//取消执行请求
            okHttpUtils = null;
        }
        super.onDestroy();
    }
}
