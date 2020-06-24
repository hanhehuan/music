package com.example.newdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.newdemo.R;
import com.example.newdemo.utils.SPUtils;
import com.example.newdemo.utils.UserUtils;
import com.example.newdemo.view.InputView;

public class ChangePassActivity extends BaseActivity {

    private InputView pass_old,pass_new,pass_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        initView();
    }

    private void initView() {
        initNavBar(true,"修改密码",false);

        pass_old = fd(R.id.input_password_old);
        pass_new = fd(R.id.input_password_new);
        pass_confirm = fd(R.id.input_password_confirm);

    }

    public void onChangPassClick(View view){
        String old_pass = pass_old.getInputStr();
        String new_pass = pass_new.getInputStr();
        String confirm_pass = pass_confirm.getInputStr();

        boolean result = UserUtils.changePassword(this,old_pass,new_pass,confirm_pass);
        if (!result)return;

        UserUtils.logout(this);
    }
}
