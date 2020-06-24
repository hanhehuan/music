package com.example.newdemo.helps;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.helps
 * 文件名： UserHelp
 * 创建者：hanhehuann
 * 创建时间：2020-06-24 13:49
 * 描述：1、用户登录
 *      2、用户退出
 */

public class UserHelp {
    private static UserHelp instance;

    private UserHelp(){}

    public static UserHelp getInstance(){
        if (instance == null){
            synchronized (UserHelp.class){
                if (instance == null){
                    instance = new UserHelp();
                }
            }
        }
        return instance;
    }

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
