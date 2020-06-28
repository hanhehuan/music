package com.example.newdemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.newdemo.R;
import com.example.newdemo.adapters.MusicListAdapter;
import com.example.newdemo.helps.RealmHelp;
import com.example.newdemo.model.AlbumModel;

public class AlbumListActivity extends BaseActivity {

    public static final String ALBUM_ID = "album_id";

    private RecyclerView rvList;
    private MusicListAdapter mAdapter;
    private String mAlbumId;
    private RealmHelp realmHelp;
    private AlbumModel albumModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        initData();
        initView();
    }

    private void initData(){
        mAlbumId = getIntent().getStringExtra(ALBUM_ID);
        realmHelp = new RealmHelp();
        albumModel = realmHelp.getAlbum(mAlbumId);
    }

    private void initView() {
        initNavBar(true,"专辑列表",false);
        rvList = fd(R.id.rv_list);
        rvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rvList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MusicListAdapter(this,null,albumModel.getList());
        rvList.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmHelp.close();
    }
}
