package com.zhuoxin.netdemo.netdemo.MVP;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zhuoxin.netdemo.netdemo.R;
import com.zhuoxin.netdemo.netdemo.User;

/**
 * Created by Dionysus on 2017/8/29.
 */

public class MVPRegisterActivity extends AppCompatActivity implements RegisterView {
    private EditText mUsername;
    private EditText mPassword;
    private String username;
    private String password;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvpregister);

        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
    }

    public void register(View view) {
        username = mUsername.getText().toString().trim();
        password = mUsername.getText().toString().trim();
        User mUser = new User();
        mUser.setUserName(username);
        mUser.setPassword(password);
        new RegisterPresenter(this).register(mUser);
    }
    //---------------------------------实现自视图接口的方法-----------------------
    @Override
    public void showProgress() {
        mProgressDialog = ProgressDialog.show(this,"注册","正在注册，不要着急哈，你急我也没办法");
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog != null){
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
