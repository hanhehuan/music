package com.example.newdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.example.newdemo.R;
import com.example.newdemo.utils.UserUtils;
import com.example.newdemo.view.InputView;

public class RegisterActivity extends BaseActivity {

    private InputView mPhone;
    private InputView mPass;
    private InputView mPass_one;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        initNavBar(true,"注册",false);//设置头部
        mPhone = fd(R.id.input_phone1);
        mPass = fd(R.id.input_password1);
        mPass_one = fd(R.id.input_password_one);
    }


    public void onRegister(View view){
        String phone = mPhone.getInputStr();
        String pass = mPass.getInputStr();
        String pass_one = mPass_one.getInputStr();
        if (!UserUtils.validateLogin(this,phone,pass)){
            return;
        }
        if (!pass.equals(pass_one)){
            Toast.makeText(this,"两次输入的密码不同，请重新输入！",Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this,"注册成功！",Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    protected void onDestroy() {
        Log.i("RegisterActivity","进入onDestroy");
        super.onDestroy();
    }
}
