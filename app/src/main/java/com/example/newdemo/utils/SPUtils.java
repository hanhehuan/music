package com.example.newdemo.utils;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.utils
 * 文件名： SPUtils
 * 创建者：hanhehuann
 * 创建时间：2020-06-24 13:55
 * 描述：TODO
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.newdemo.constants.SPConstans;
import com.example.newdemo.helps.UserHelp;

public class SPUtils {
    public static boolean saveUser(Context context,String phone){
        SharedPreferences sp = context.getSharedPreferences(SPConstans.SP_NAME_USER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SPConstans.SP_KEY_PHONE,phone);
        boolean result = sp.edit().commit();
        return  result;
    }

    /**
     * 验证是否存在已登录用户
     */
    public static boolean isLoginUser(Context context){
        boolean result = false;

        SharedPreferences sp = context.getSharedPreferences(SPConstans.SP_NAME_USER,Context.MODE_PRIVATE);
        String phone = sp.getString(SPConstans.SP_KEY_PHONE,"");

        if (!TextUtils.isEmpty(phone)){
            result = true;
            UserHelp.getInstance().setPhone(phone);
        }

        return result;
    }

    /**
     * 删除用户标记
     */
    public static boolean removeUser(Context context){
        SharedPreferences sp = context.getSharedPreferences(SPConstans.SP_NAME_USER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(SPConstans.SP_KEY_PHONE);
        boolean commit = editor.commit();
        return commit;

    }

}
