package com.example.newdemo.utils;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.utils
 * 文件名： UserUtils
 * 创建者：hanhehuann
 * 创建时间：2020-06-16 17:46
 * 描述：TODO
 */

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;

public class UserUtils {
    /**
     * 验证用户登录合法性
     */

    public static boolean validateLogin(Context context,String phone,String password){
        //简单的
        //RegexUtils.isMobileSimple(phone);

        //精确的
        if (!RegexUtils.isMobileExact(phone)){
            Toast.makeText(context,"无效的手机号",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(context,"请输入密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
