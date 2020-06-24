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

        mPath = path;
        /**
         * 1、音乐正在播放，重置音乐状态
         * 2、设置音乐播放路径
         * 3、准备播放
         */
        //音乐正在播放，重置音乐状态
        if (mediaPlayer.isPlaying()){
            mediaPlayer.reset();
        }
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
    }

}
