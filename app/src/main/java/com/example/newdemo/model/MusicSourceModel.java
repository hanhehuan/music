package com.example.newdemo.model;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.model
 * 文件名： MusicSourceModel
 * 创建者：hanhehuann
 * 创建时间：2020-06-28 10:57
 * 描述：TODO
 */

import io.realm.RealmList;
import io.realm.RealmObject;

public class MusicSourceModel extends RealmObject {

    private RealmList<AlbumModel> album;
    private RealmList<MusicModel> hot;

    public RealmList<AlbumModel> getAlbum() {
        return album;
    }

    public void setAlbum(RealmList<AlbumModel> album) {
        this.album = album;
    }

    public RealmList<MusicModel> getHot() {
        return hot;
    }

    public void setHot(RealmList<MusicModel> hot) {
        this.hot = hot;
    }
}
