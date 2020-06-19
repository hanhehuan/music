package com.example.newdemo.utils;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.utils
 * 文件名： ResultUtil
 * 创建者：hanhehuann
 * 创建时间：2020-06-15 14:50
 * 描述：TODO
 */

public class ResultUtil {

    public static ResultData success(Object object){
        return new ResultData(RetCode.SUCCESS,"请求成功",object);
    }

    public static ResultData success(){
        return success(null);
    }

    public static ResultData error(RetCode retCode,String msg){
        return new ResultData(retCode,msg,null);
    }
}
