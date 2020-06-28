package com.example.newdemo.helps;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.helps
 * 文件名： MediaPlayerHelp
 * 创建者：hanhehuann
 * 创建时间：2020-06-24 9:07
 * 描述：TODO
 */

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;
import java.security.PublicKey;

/**
 * 1、直接在activity中创建播放音乐，音乐与activity绑定，activity运行时播放音乐，activity退出时音乐停止
 * 2、通过全局单例类与application绑定，application运行时播放音乐，application停止时音乐停止
 * 3、通过service进行音乐播放，service运行时音乐播放，service停止时音乐停止
 */

public class MediaPlayerHelp {

    private static MediaPlayerHelp instance;
    private String mPath;
    private Context mContext;
    private MediaPlayer mediaPlayer;
    private OnMediaPlayerHelperLister lister;

    public void setLister(OnMediaPlayerHelperLister lister) {
        this.lister = lister;
    }

    public static MediaPlayerHelp getInstance(Context context){
        if (instance == null){
            synchronized (MediaPlayerHelp.class){
                if (instance == null){
                    instance = new MediaPlayerHelp(context);
                }
            }
        }
        return instance;
    }

    private MediaPlayerHelp(Context context){
        mContext = context;
        mediaPlayer = new MediaPlayer();
    }

    /**
     * 1、setPath:当前需要播放的音乐
     * 2、start:播放音乐
     * 3、pause：暂停音乐
     */

    public void setPath(String path){
        /**
         * 1、音乐正在播放，重置音乐状态
         * 2、设置音乐播放路径
         * 3、准备播放
         */
        mPath = path;
        /**
         * （错误逻辑！）当进行音乐切换的时候如果音乐处于播放状态，则重置音乐状态
         * 如果音乐不处于播放状态（暂停），则不重置
         */

        //音乐正在播放或者切换了音乐，重置音乐状态
        if (mediaPlayer.isPlaying() || !path.equals(mPath) ){
            mediaPlayer.reset();
        }

        mPath = path;

        //设置音乐播放路径
        try {
            mediaPlayer.setDataSource(mContext, Uri.parse(path));
        }catch (IOException e){
            e.printStackTrace();
        }
        //准备播放
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                if (lister != null){
                    lister.onPrepared(mediaPlayer);
                }
            }
        });
        //监听音乐播放完成
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (lister != null){
                    lister.onCompletion(mediaPlayer);
                }
            }
        });
    }

    public String getPath(){
        return mPath;
    }

    /**
     * 音乐开始
     */
    public void start(){
        if (mediaPlayer.isPlaying())return;
        mediaPlayer.start();
    }

    /**
     * 音乐暂停
     */
    public void pause(){
        mediaPlayer.pause();
    }

    public interface OnMediaPlayerHelperLister{
        void onPrepared(MediaPlayer mp);
        void onCompletion(MediaPlayer mp);
    }

}
