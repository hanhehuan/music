package com.example.newdemo.view;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.view
 * 文件名： PlayMusicView
 * 创建者：hanhehuann
 * 创建时间：2020-06-23 15:10
 * 描述：TODO
 */

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.newdemo.R;
import com.example.newdemo.helps.MediaPlayerHelp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class PlayMusicView extends FrameLayout {

    private Context mContenx;
    private MediaPlayerHelp mediaPlayerHelp;
    private String mPath;
    private View mView;
    private boolean isPlay;
    private FrameLayout mFlPlayMusic;
    private ImageView ivIcon,ivNeedle,ivPlay;
    private Animation mPlayMusicAnim,mPlayNeedleAnim,mStopNeedleAnim;

    public PlayMusicView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        //mediaplayer


        mContenx = context;
        mView = LayoutInflater.from(mContenx).inflate(R.layout.play_music,this,false);
        mFlPlayMusic = mView.findViewById(R.id.fl_play_music);
        mFlPlayMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                trigger();
            }
        });
        ivIcon = mView.findViewById(R.id.iv_icon);
        ivNeedle = mView.findViewById(R.id.iv_needle);
        ivPlay = mView.findViewById(R.id.iv_play);

        //1、定义所要执行的动画
        //1、1光盘转动所要执行的动画
        //1、2指针指向光盘的动画
        //1、3指针离开光盘的动画
        //2、startAnimation

        mPlayMusicAnim = AnimationUtils.loadAnimation(mContenx,R.anim.play_music_anim);
        mPlayNeedleAnim = AnimationUtils.loadAnimation(mContenx,R.anim.play_needle_anim);
        mStopNeedleAnim = AnimationUtils.loadAnimation(mContenx,R.anim.stop_needle_anim);



        addView(mView);

        mediaPlayerHelp = MediaPlayerHelp.getInstance(mContenx);
    }

    /**
     * 切换播放状态
     */
    private void trigger(){
        if (isPlay){
            stopPlay();
        }else {
            playMusic(mPath);
        }
    }

    /**
     * 播放音乐
     */
    public void playMusic(String path){
        mPath = path;

        isPlay = true;
        ivPlay.setVisibility(View.GONE);
        mFlPlayMusic.startAnimation(mPlayMusicAnim);
        ivNeedle.startAnimation(mPlayNeedleAnim);

        /**
         * 播放音乐
         * 1、判断当前音乐是否已经在播放
         * 2、如果当前已经在播放，自己执行start方法
         * 3、如果当前播放的音乐不是需要播放的音乐的话，那么就调用setpath方法
         */

        if (mediaPlayerHelp.getPath() != null && path.equals(mediaPlayerHelp.getPath())){
            mediaPlayerHelp.start();
        }else {
            mediaPlayerHelp.setPath(path);
            mediaPlayerHelp.setLister(new MediaPlayerHelp.OnMediaPlayerHelperLister() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayerHelp.start();
                }
            });
        }
    }

    /**
     * 停止播放
     */
    public void stopPlay(){
        isPlay = false;
        ivPlay.setVisibility(View.VISIBLE);
        mFlPlayMusic.clearAnimation();
        ivNeedle.startAnimation(mStopNeedleAnim);

        mediaPlayerHelp.pause();
    }

    /**
     * 设置光盘中显示的音乐封面图片
     * @param icon
     */
    public void setMusicIcon(String icon){
        Glide.with(mContenx)
                .load(icon)
                .into(ivIcon);
    }
}
