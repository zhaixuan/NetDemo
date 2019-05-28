package com.zhuoxin.netdemo.netdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dionysus on 2017/8/28.
 */

public class RetrofitActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
    }
    //-----------------------------------get请求-----------------------------------------------
    public void get(View view) {
        RetrofitClient.getInstance().getAPI().getRequest("search","language:java","1").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    ResponseBody mBody = response.body();
                    try {
                        Toast.makeText(RetrofitActivity.this,"成功"+mBody.string(),Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this,"失败"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    //-----------------------------------post请求-----------------------------------------------
    public void post(View view) {
        User mUser = new User();
        RetrofitClient.getInstance().getAPI().postRequest(mUser).enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                if (response.isSuccessful()){
                    UserResult body = response.body();
                    try {
                        Toast.makeText(RetrofitActivity.this,"成功"+body.toString(),Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this,"失败"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    //-----------------------------------表格请求-----------------------------------------------
    public void form(View view) {
        RetrofitClient.getInstance().getAPI().formQuest("张三","1212123").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    ResponseBody body = response.body();
                    try {
                        Toast.makeText(RetrofitActivity.this,"成功"+body.string(),Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this,"失败"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    //-----------------------------------多部分请求-----------------------------------------------
    public void multipart(View view) {
        MultipartBody.Part part = MultipartBody.Part.createFormData("aaa","{\n" +
                "\"name\": \"yt59856b15cf394e7b84a7d48447d16098\",\n" +
                "\"username\": \"xc62\",\n" +
                "\"nickname\": \"555\",\n" +
                "\"password\": \"123456\",\n" +
                "\"uuid\": \"0F8EC12223174657B2E842076D54C361\"\n" +
                "}");
        RetrofitClient.getInstance().getAPI().multiRequest(part).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    ResponseBody body = response.body();
                    try {
                        Toast.makeText(RetrofitActivity.this,"成功"+body.string(),Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this,"失败"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
