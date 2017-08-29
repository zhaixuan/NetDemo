package com.zhuoxin.netdemo.netdemo;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by MACHENIKE on 2017/8/28.
 */

public abstract class UICallBack implements Callback{

    Handler mHandler = new Handler(Looper.getMainLooper());
    @Override
    public void onFailure(final Call call, final IOException e) {
        //子线程
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //主线程
                onFailureInUI(call,e);
            }
        });
    }

    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        //子线程
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //主线程
                try {
                    onResponseInUI(call,response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public abstract void onFailureInUI(Call call, IOException e);
    public abstract void onResponseInUI(Call call, Response response) throws IOException;

}
