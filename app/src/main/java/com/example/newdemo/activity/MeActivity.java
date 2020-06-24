package com.example.newdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.newdemo.R;
import com.example.newdemo.helps.UserHelp;
import com.example.newdemo.utils.UserUtils;

public class MeActivity extends BaseActivity {

    private TextView tvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        initView();
    }

    private void initView() {
        initNavBar(true,"个人中心",false);
        tvUser = fd(R.id.tv_user);
        tvUser.setText("用户名：" + UserHelp.getInstance().getPhone());
    }

    /**
     * 修改密码
     * @param view
     */
    public void onChangClick(View view){
        startActivity(new Intent(this,ChangePassActivity.class));
    }

    /**
     * 退出登录
     * @param view
     */
    public void onLogoutClick(View view){
        UserUtils.logout(this);
    }
}
