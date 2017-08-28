package com.zhuoxin.netdemo.netdemo;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Dionysus on 2017/8/28.
 */

public class HttpClient {
    private static HttpClient httpClient;
    private OkHttpClient mOkHttpClient;

    private HttpClient (){
        mOkHttpClient = new OkHttpClient.Builder().build();
    }

    public static synchronized HttpClient getInstance(){
        if (httpClient == null){
            httpClient = new HttpClient();
        }
        return httpClient;
    }

    public Call getRequest(){
        Request mRequest = new Request.Builder()
                .url("https://api.github.com/search/repositories")
                .build();
        return mOkHttpClient.newCall(mRequest);
    }
}
