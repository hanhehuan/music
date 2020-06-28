package com.example.newdemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.RealmObject;
import jp.wasabeef.glide.transformations.BlurTransformation;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.newdemo.R;
import com.example.newdemo.helps.RealmHelp;
import com.example.newdemo.model.MusicModel;
import com.example.newdemo.view.PlayMusicView;

public class PlayMusicActivity extends BaseActivity {

    public static final String MUSIC_ID = "musicId";

    private ImageView ivBg;
    private PlayMusicView mPlayMusicView;
    private RealmHelp realmHelp;
    private MusicModel musicModel;
    private String mMusicId;
    private TextView mName,mAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        //隐藏statusbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initView();
    }

    private void initData(){
        mMusicId = getIntent().getStringExtra(MUSIC_ID);
        realmHelp = new RealmHelp();
        musicModel = realmHelp.getMusic(mMusicId);

    }

    private void initView(){
        ivBg = fd(R.id.iv_bg);
        mName = fd(R.id.tv_name);
        mAuthor=fd(R.id.tv_author);
        //glid-tranformations
        //"http://res.lgdsunday.club/poster-1.png"
        Glide.with(this)
                .load(musicModel.getPoster())
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25,10)))
                .into(ivBg);

        mName.setText(musicModel.getName());
        mAuthor.setText(musicModel.getAuthor());

        mPlayMusicView = fd(R.id.play_music_view);
        //"http://res.lgdsunday.club/poster-1.png"
        mPlayMusicView.setMusicIcon();
        mPlayMusicView.setMusic(musicModel);
        //"http://res.lgdsunday.club/Nostalgic%20Piano.mp3"
        mPlayMusicView.playMusic();
    }

    public void onBackClick(View view){
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayMusicView.destory();
        realmHelp.close();
    }
}
