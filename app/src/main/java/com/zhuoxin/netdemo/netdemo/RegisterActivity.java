package com.zhuoxin.netdemo.netdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dionysus on 2017/8/29.
 */

public class RegisterActivity extends AppCompatActivity {
    private EditText mUsername;
    private EditText mPassword;
    private String username;
    private String password;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
    }

    public void register(View view) {
        username = mUsername.getText().toString().trim();
        password = mPassword.getText().toString().trim();

        User mUser = new User();
        mUser.setUserName(username);
        mUser.setPassword(password);

        mProgressDialog = ProgressDialog.show(this,"注册","正在玩命的注册中........");
        RetrofitClient.getInstance().getAPI().postRequest(mUser).enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                mProgressDialog.dismiss();
                if (response.isSuccessful()){
                    UserResult mResult = response.body();
                    if (mResult != null){
                        if (mResult.getErrcode() == 1){
                            Toast.makeText(RegisterActivity.this,"注册成功！！",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(RegisterActivity.this,mResult.getErrmsg(),Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(RegisterActivity.this,"未知错误！",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
