package com.example.newdemo.utils;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.utils
 * 文件名： OkHttpUtils
 * 创建者：hanhehuann
 * 创建时间：2020-06-12 13:57
 * 描述：TODO
 */

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils {
    private Context context;
    private HttpCallBack callBack;
    private Call call;

    public OkHttpUtils(Context context,HttpCallBack callBack){
        this.context = context;
        this.callBack = callBack;
    }

    /**
     * 同步get请求
     * @param url
     */
    public void getSyn(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();
                call = client.newCall(request);
                Response response = call.execute();
                if (response.isSuccessful()){
                    String result = response.body().string();
                    //处理响应
                    callBack.success(ResultUtil.success(result).toString());
                    //Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
                }
                } catch (IOException e) {
                    e.printStackTrace();
                    callBack.failure(ResultUtil.error(RetCode.FAIL,e.getMessage()).toString());
                    /*if (e.toString().contains("closed")){
                        //主动取消
                    }else if (e.toString().contains("connect")){
                        //网络出错
                    }else {
                        //超时java.net.SocketTimeoutException
                    }*/

                }

            }
        }).start();
    }

    /**
     * 异步get请求
     * @param url
     */
    public void getAsyn(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.failure(ResultUtil.error(RetCode.FAIL,e.getMessage()).toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String result = response.body().string();
                    //Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
                    callBack.success(ResultUtil.success(result).toString());
                }
            }
        });
    }

    /**
     * post请求一对值
     * @param url
     * @param key
     * @param value
     */
    public void post(String url,String key,String value){
        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add(key,value)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.failure(ResultUtil.error(RetCode.FAIL,e.getMessage()).toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String result = response.body().string();
                    callBack.success(ResultUtil.success(result).toString());
                }
            }
        });
    }

    /**
     * 上传文件
     * @param url
     * @param file
     */
    public void uploadFile(String url, File file) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body =  RequestBody.create(MediaType.get("application/octet-stream"), file);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.failure(ResultUtil.error(RetCode.FAIL,e.getMessage()).toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String result = response.body().string();
                    callBack.success(ResultUtil.success(result).toString());
                }
            }
        });
    }

    /**
     * post传多个键值对
     * @param url
     * @param object
     */
    public void postByObject(String url, Object object) {
        OkHttpClient client = new OkHttpClient();
        MediaType jsonType = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();
        String jsonstr = gson.toJson(object);
        RequestBody body =  RequestBody.create(jsonType, jsonstr);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.failure(ResultUtil.error(RetCode.FAIL,e.getMessage()).toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String result = response.body().string();
                    callBack.success(ResultUtil.success(result).toString());
                }
            }
        });
    }

    public void cancelHttp(){
        if (call.isExecuted()){//正在执行
            call.cancel();
            call = null;
        }
    }


    /*MultipartBody body = new MultipartBody.Builder()
//      添加表单参数
//      .addFormDataPart(key,value)
            .addFormDataPart(name, fileName, RequestBody.create(MediaType.get("application/octet-stream"), file))
            .build();*/


    public interface HttpCallBack{
        void success(String msg);
        void failure(String e);
    }
}
