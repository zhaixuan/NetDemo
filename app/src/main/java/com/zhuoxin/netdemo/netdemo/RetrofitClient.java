package com.zhuoxin.netdemo.netdemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
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
