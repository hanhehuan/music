package com.example.newdemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.newdemo.R;
import com.example.newdemo.adapters.MusicGridAdapter;
import com.example.newdemo.adapters.MusicListAdapter;
import com.example.newdemo.utils.OkHttpUtils;
import com.example.newdemo.utils.ResultData;
import com.example.newdemo.utils.ResultUtil;
import com.example.newdemo.view.GridSpaceItemDecoration;
import com.google.gson.Gson;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button btn;
    private RecyclerView mRvGrid,mRvList;
    private MusicGridAdapter mGridAdapter;
    private MusicListAdapter mListAdapter;
    //private OkHttpUtils okHttpUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*btn = findViewById(R.id.button);
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
        });*/

        initView();
    }

    private void initView() {
        initNavBar(false,"慕课音乐",true);

        mRvGrid = fd(R.id.rv_grid);
        mRvGrid.setLayoutManager(new GridLayoutManager(this,3));
        mRvGrid.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize),mRvGrid));
        mRvGrid.setNestedScrollingEnabled(false);
        mGridAdapter = new MusicGridAdapter(this);
        mRvGrid.setAdapter(mGridAdapter);
        /**
         * 1、加入已知列表高度的情况下，可以直接在布局中吧recyclerview的高度定义上
         * 2、不知道列表高度的情况下，需要手动计算recyclerview的高度
         *
         */
        mRvList = fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRvGrid.setNestedScrollingEnabled(false);
        mListAdapter = new MusicListAdapter(this,mRvList);
        mRvList.setAdapter(mListAdapter);

    }

    @Override
    public void onClick(View view) {
        Log.i(TAG,"点击了按钮");
        /*okHttpUtils.getSyn("http://www.baidu.com");*/
    }

    @Override
    protected void onDestroy() {
        /*if (okHttpUtils != null){
            okHttpUtils.cancelHttp();//取消执行请求
            okHttpUtils = null;
        }*/
        super.onDestroy();
    }
}
