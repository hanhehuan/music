package com.example.newdemo.activity;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.activity
 * 文件名： BaseActivity
 * 创建者：hanhehuann
 * 创建时间：2020-06-16 9:22
 * 描述：TODO
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newdemo.R;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private ImageView img_back,img_me;
    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
    }

    /**
     * findViewById
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T fd(@IdRes int id){
        return findViewById(id);
    }

    /**
     * 初始化NavBar
     * @param showBack
     * @param title
     * @param showMe
     */
    protected void initNavBar(boolean showBack,String title,boolean showMe){



        img_back = fd(R.id.iv_back);
        img_me = fd(R.id.iv_me);
        mTitle = fd(R.id.tv_title);

        img_back.setVisibility(showBack?View.VISIBLE:View.GONE);
        img_me.setVisibility(showMe?View.VISIBLE:View.GONE);
        mTitle.setText(title);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        img_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BaseActivity.this,MeActivity.class));
            }
        });
    }
}
