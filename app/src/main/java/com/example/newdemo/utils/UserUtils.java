package com.example.newdemo.utils;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.utils
 * 文件名： UserUtils
 * 创建者：hanhehuann
 * 创建时间：2020-06-16 17:46
 * 描述：TODO
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.example.newdemo.R;
import com.example.newdemo.activity.LoginActivity;
import com.example.newdemo.helps.RealmHelp;
import com.example.newdemo.helps.UserHelp;
import com.example.newdemo.model.UserModel;

import java.util.List;

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

        /**
         * 1、用户当前手机号是否已经被注册了
         * 2、用户输入的手机号和密码是否匹配
         */
        if (!UserUtils.userExistFromPhone(phone)){
            Toast.makeText(context,"当前手机号未注册",Toast.LENGTH_SHORT).show();
        }

        RealmHelp realmHelp = new RealmHelp();
        boolean result = realmHelp.validateUser(phone,EncryptUtils.encryptMD5ToString(password));
        realmHelp.close();
        if (!result){
            Toast.makeText(context,"手机号或密码不正确",Toast.LENGTH_SHORT).show();
            return false;
        }
        //保存用户登录标记
        boolean isSave = SPUtils.saveUser(context,phone);
        if (!isSave){
            Toast.makeText(context,"系统错误，请稍后重试",Toast.LENGTH_SHORT).show();
            return false;
        }
        //保存用户标记
        UserHelp.getInstance().setPhone(phone);
        //保存音乐源
        realmHelp.setMusicSource(context);

        realmHelp.close();
        return true;
    }

    /**
     * 退出登录
     * @param context
     */
    public static void logout(Context context){
        //删除sp保存的用户信息
        boolean b = SPUtils.removeUser(context);
        if (!b){
            Toast.makeText(context,"系统错误，请稍后重试",Toast.LENGTH_SHORT).show();
            return;
        }

        //删除音乐源
        RealmHelp realmHelp = new RealmHelp();
        realmHelp.removeMusicSource();
        realmHelp.close();

        Intent intent = new Intent(context, LoginActivity.class);
        //设置intent标识符，清理task栈并且新生成一个新的task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //定义activity跳转动画
        ((Activity)context).overridePendingTransition(R.anim.open_enter,R.anim.open_exit);

    }

    /**
     * 注册用户
     * @param context
     * @param phone
     * @param pass
     * @param password
     */
    public static boolean register(Context context,String phone,String pass,String password){
        //精确的
        if (!RegexUtils.isMobileExact(phone)){
            Toast.makeText(context,"无效的手机号",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (StringUtils.isEmpty(pass) || !pass.equals(password)){
            Toast.makeText(context,"请确认密码",Toast.LENGTH_SHORT).show();
            return false;
        }

        //用户当前输入的手机号是否已被注册

        if (UserUtils.userExistFromPhone(phone)){
            Toast.makeText(context,"该手机号已存在",Toast.LENGTH_SHORT).show();
            return false;
        }

        UserModel userModel = new UserModel();
        userModel.setPhone(phone);
        userModel.setPassword(EncryptUtils.encryptMD2ToString(pass));
        saveUser(userModel);
        Toast.makeText(context,"注册成功",Toast.LENGTH_SHORT).show();
        return true;
    }

    public static void saveUser(UserModel userModel){
        RealmHelp realmHelp = new RealmHelp();
        realmHelp.saveUser(userModel);
        realmHelp.close();
    }

    /**
     * 根据手机号判断是否已经注册
     * @param phone
     * @return
     */
    public static boolean userExistFromPhone(String phone){
        boolean result = false;

        RealmHelp realmHelp = new RealmHelp();
        List<UserModel> userModelList = realmHelp.getAllUser();
        for (UserModel userModel:userModelList){
            if (userModel.getPhone().equals(phone)){
                result = true;//已经存在手机号
                break;
            }
        }
        realmHelp.close();

        return result;
    }

    /**
     * 验证是否存在已登录用户
     * @param context
     * @return
     */
    public static boolean validateUserLogin(Context context){
        return SPUtils.isLoginUser(context);
    }

    /**
     * 修改密码
     */
    public static boolean changePassword(Context context,String pass_old,String pass_new,String pass_confirm){
        if (TextUtils.isEmpty(pass_old)){
            Toast.makeText(context,"请输入原密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(pass_new) || !pass_new.equals(pass_confirm)){
            Toast.makeText(context,"请确认密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        //验证密码是否正确
        RealmHelp realmHelp = new RealmHelp();
        UserModel userModel = realmHelp.getUser();
        if (!EncryptUtils.encryptMD5ToString(pass_old).equals(userModel.getPassword())){
            Toast.makeText(context,"原密码不正确",Toast.LENGTH_SHORT).show();
            return false;
        }

        realmHelp.changePassword(EncryptUtils.encryptMD5ToString(pass_new));

        realmHelp.close();

        return true;
    }
}
