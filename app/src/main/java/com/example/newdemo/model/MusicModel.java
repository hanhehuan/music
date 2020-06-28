package com.example.newdemo.model;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.model
 * 文件名： MusicModel
 * 创建者：hanhehuann
 * 创建时间：2020-06-28 11:03
 * 描述：TODO
 */

import io.realm.RealmObject;

public class MusicModel extends RealmObject {
    /**
     * {
     *           "musicId": "101",
     *           "name": "Nostalgic Piano",
     *           "poster": "http://res.lgdsunday.club/poster-1.png",
     *           "path": "http://res.lgdsunday.club/Nostalgic%20Piano.mp3",
     *           "author": "Rafael Krux"
     *         },
     */

    private String musicId;
    private String name;
    private String poster;
    private String path;
    private String author;

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
