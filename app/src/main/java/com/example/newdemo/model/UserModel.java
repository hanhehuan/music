package com.example.newdemo.model;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.model
 * 文件名： UserModel
 * 创建者：hanhehuann
 * 创建时间：2020-06-24 10:09
 * 描述：TODO
 */

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class UserModel extends RealmObject {
    @PrimaryKey
    private String phone;
    @Required
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
