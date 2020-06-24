package com.example.newdemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.newdemo.R;
import com.example.newdemo.adapters.MusicListAdapter;

public class AlbumListActivity extends BaseActivity {

    private RecyclerView rvList;
    private MusicListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);

        initView();
    }

    private void initView() {
        initNavBar(true,"专辑列表",false);
        rvList = fd(R.id.rv_list);
        rvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rvList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MusicListAdapter(this,null);
        rvList.setAdapter(mAdapter);
    }
}
