package com.example.newdemo.utils;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.utils
 * 文件名： RetCode
 * 创建者：hanhehuann
 * 创建时间：2020-06-15 17:05
 * 描述：TODO
 */

public enum RetCode {
    //成功
    SUCCESS(200),
    //失败
    FAIL(400),
    //未认证（签名错误）
    UNAUTHORIZED(401),
    //接口不存在
    NOT_FOUND(404),
    //服务器内部错误
    INTERNAL_SERVER_ERROR(500);

    public int code;
    RetCode(int code){
        this.code = code;
    }
}
