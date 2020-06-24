package com.example.newdemo.helps;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.helps
 * 文件名： RealmHelp
 * 创建者：hanhehuann
 * 创建时间：2020-06-24 10:08
 * 描述：TODO
 */

import com.example.newdemo.model.UserModel;
import com.example.newdemo.utils.UserUtils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmHelp {

    private Realm mRealm;
    public RealmHelp(){
        //获取realm对象
        mRealm = Realm.getDefaultInstance();
    }

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
}
