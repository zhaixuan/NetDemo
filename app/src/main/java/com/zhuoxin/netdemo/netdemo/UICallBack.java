package com.zhuoxin.netdemo.netdemo;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Dionysus on 2017/8/14.
 */

public abstract class UICallBack implements Callback{
    //拿到主线程的handler Looper也是主线程的。无需重写HandleMessage方法
    Handler handle = new Handler(Looper.getMainLooper());
    @Override//子线程
    public void onFailure(final Call call, final IOException e) {
        handle.post(new Runnable() {
            @Override
            public void run() {
                //此方法在主线程里执行
                onFailureUI(call,e);
            }
        });
    }

    @Override//子线程
    public void onResponse(final Call call,final Response response) throws IOException {
        final String ms = response.body().string();//将响应变成字符串
        //判断响应是否成功
        if (response.equals("") && !response.isSuccessful()){
            //失败
            throw new IOException("error code:" + response.code());//例如code=404
        }
        handle.post(new Runnable() {
            @Override
            public void run() {
                onResponseUI(call ,ms);//此方法在主线程里回调
            }
        });
    }
    public abstract void onFailureUI(Call call ,IOException e);//此回调方法执行在主线程中
    public abstract void onResponseUI(Call call ,String body);
}