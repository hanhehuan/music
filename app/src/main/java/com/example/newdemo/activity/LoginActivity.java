package com.example.newdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.newdemo.R;
import com.example.newdemo.utils.UserUtils;
import com.example.newdemo.view.InputView;

public class LoginActivity extends BaseActivity {

    private InputView mPhone;
    private InputView mPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();
    }


    private void initView() {
        initNavBar(false,"登录",false);//设置头部
        mPhone = fd(R.id.input_phone);
        mPass = fd(R.id.input_password);
    }
    private void initData() {

    }

    /**
     * 跳转到注册页面
     */
    public void onRegisterClick(View view){
        startActivity(new Intent(this,RegisterActivity.class));
    }

    /**
     * 登录
     */
    public void onCommitClick(View view){
        String phone = mPhone.getInputStr();
        String pass = mPass.getInputStr();
        //验证用户输入是否合法
        if (!UserUtils.validateLogin(this,phone,pass)){
            return;
        }



        //跳转到主页面去
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
