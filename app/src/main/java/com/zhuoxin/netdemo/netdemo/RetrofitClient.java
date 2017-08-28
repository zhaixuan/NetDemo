package com.zhuoxin.netdemo.netdemo;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by Dionysus on 2017/8/28.
 */

public class RetrofitClient {
    private static RetrofitClient mRetrofitClient;
    private Retrofit mRetrofit;

    private RetrofitClient (){
        HttpLoggingInterceptor mInterceptor = new HttpLoggingInterceptor();
        mInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(mInterceptor)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .client(mOkHttpClient)
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if (mRetrofitClient==null){
            mRetrofitClient = new RetrofitClient();
        }
        return mRetrofitClient;
    }

    public RetrofitAPIs getAPI(){
        return mRetrofit.create(RetrofitAPIs.class);
    }
}
