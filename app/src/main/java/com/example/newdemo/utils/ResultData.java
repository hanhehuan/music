package com.example.newdemo.utils;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.utils
 * 文件名： ResultData
 * 创建者：hanhehuann
 * 创建时间：2020-06-15 14:37
 * 描述：TODO
 */

import androidx.annotation.NonNull;

public class ResultData {

    private Integer code;

    private String msg;

    private Object data;

    public ResultData(RetCode retCode,String msg,Object data){
        this.code = retCode.code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
