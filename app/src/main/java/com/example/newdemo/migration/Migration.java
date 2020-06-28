package com.example.newdemo.migration;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.migration
 * 文件名： Migration
 * 创建者：hanhehuann
 * 创建时间：2020-06-28 13:17
 * 描述：TODO
 */

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * private String musicId;
 *     private String name;
 *     private String poster;
 *     private String path;
 *     private String author;
 */
public class Migration implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        RealmSchema schema = realm.getSchema();


        /**
         * 第一次迁移
         */
        if (oldVersion == 0){

            schema.create("MusicModel")
                    .addField("musicId",String.class)
                    .addField("name",String.class)
                    .addField("poster",String.class)
                    .addField("path",String.class)
                    .addField("author",String.class);

            schema.create("AlbumModel")
                    .addField("albumId",String.class)
                    .addField("name",String.class)
                    .addField("poster",String.class)
                    .addField("playNum",String.class)
                    .addRealmListField("list",schema.get("MusicModel"));

            schema.create("MusicSourceModel")
                    .addRealmListField("album",schema.get("AlbumModel"))
                    .addRealmListField("hot",schema.get("MusicModel"));

            oldVersion = newVersion;

        }
    }
}
