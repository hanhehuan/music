package com.example.newdemo.utils;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.utils
 * 文件名： DataUtils
 * 创建者：hanhehuann
 * 创建时间：2020-06-28 11:23
 * 描述：TODO
 */

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataUtils {
    /**
     *读取资源文件中的数据
     * @return
     */
    public static String getJsonFromAssets(Context context,String filename){
        /**
         * 1、StringBuilder 存放读取出的数据
         * 2、AssetsManage 资源管理器，open 方法打开指定的资源文件，返回InputStream
         * 3、InputStreamReader(字节到字符的桥接器），bufferReader（存放读取字符的缓存区）
         * 4、循环读取bufferReader 的readLine方法读取每一行的数据，并且把读取的数据放入StringBuilder中
         * 5、返回读取出来的所有数据。
         */
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetsManage = context.getAssets();
        try  {
            InputStream inputStream = assetsManage.open(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
