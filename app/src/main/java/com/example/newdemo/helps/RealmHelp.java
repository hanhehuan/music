package com.example.newdemo.helps;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.helps
 * 文件名： RealmHelp
 * 创建者：hanhehuann
 * 创建时间：2020-06-24 10:08
 * 描述：TODO
 */

import android.content.Context;

import com.example.newdemo.activity.AlbumListActivity;
import com.example.newdemo.migration.Migration;
import com.example.newdemo.model.AlbumModel;
import com.example.newdemo.model.MusicModel;
import com.example.newdemo.model.MusicSourceModel;
import com.example.newdemo.model.UserModel;
import com.example.newdemo.utils.DataUtils;
import com.example.newdemo.utils.UserUtils;

import java.io.FileNotFoundException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmHelp {

    private Realm mRealm;
    public RealmHelp(){
        //获取realm对象
        mRealm = Realm.getDefaultInstance();
    }
    /**
     * 告诉realm数据库需要迁移，并且为realm设置最新的配置
     */
    public static void migration(){
        RealmConfiguration configuration = getRealmConf();
        //设置最新的配置
        Realm.setDefaultConfiguration(configuration);
        //告诉realm数据需要迁移
        try {
            Realm.migrateRealm(configuration);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Realm数据库结果发生变化时需要对数据库进行 迁移
     */

    private static RealmConfiguration getRealmConf(){
        return new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new Migration())
                .build();
    }

    /**
     * 关闭数据库
     */
    public void close(){
        if (mRealm != null && !mRealm.isClosed()){
            mRealm.close();
        }
    }

    /**
     * 保存用户信息
     * @param userModel
     */
    public void saveUser(UserModel userModel){
        mRealm.beginTransaction();//开始事务
        mRealm.insert(userModel);
        //mRealm.insertOrUpdate(userModel);
        mRealm.commitTransaction();//提交事务
    }
    public List<UserModel> getAllUser(){
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        RealmResults<UserModel> results = query.findAll();
        return results;
    }

    public boolean validateUser(String phone,String pass){
        boolean result = false;
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        query = query.equalTo("phone",phone).equalTo("password",pass);
        UserModel userModel = query.findFirst();
        if (userModel != null){
            result = true;
        }
        return result;
    }

    /**
     * 获取当前用户
     */

    public UserModel getUser(){
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        query = query.equalTo("phone", UserHelp.getInstance().getPhone());
        UserModel userModel = query.findFirst();
        return userModel;
    }

    /**
     * 修改密码
     */
    public void changePassword(String password){
        UserModel userModel = getUser();
        mRealm.beginTransaction();
        userModel.setPassword(password);
        mRealm.commitTransaction();
    }

    /**
     * 1、用户登录，存放数据
     * 2、用户退出，删除数据
     */

    /**
     * 保存音乐数据
     */

    public void setMusicSource(Context context){
        //拿到资源文件中的数据
        String musicDataJson = DataUtils.getJsonFromAssets(context,"DataSource.json");
        mRealm.beginTransaction();
        mRealm.createObjectFromJson(MusicSourceModel.class,musicDataJson);
        mRealm.commitTransaction();

    }

    /**
     * 删除音乐源数据
     */
    public void removeMusicSource(){
        mRealm.beginTransaction();
        mRealm.delete(MusicSourceModel.class);
        mRealm.delete(MusicModel.class);
        mRealm.delete(AlbumModel.class);
        mRealm.commitTransaction();
    }
    /**
     * 返回音乐源数据
     *
     */
    public  MusicSourceModel getMusicSourceModel(){
        return mRealm.where(MusicSourceModel.class).findFirst();
    }
    /**
     * 返回歌单
     */
    public AlbumModel getAlbum(String albumId){
        return mRealm.where(AlbumModel.class).equalTo("albumId",albumId).findFirst();
    }

    public MusicModel getMusic(String musicId){
        return mRealm.where(MusicModel.class).equalTo("musicId",musicId).findFirst();
    }
}
