package com.zhuoxin.netdemo.netdemo.MVP;

import com.zhuoxin.netdemo.netdemo.RetrofitClient;
import com.zhuoxin.netdemo.netdemo.User;
import com.zhuoxin.netdemo.netdemo.UserResult;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dionysus on 2017/8/29.
 */

public class RegisterPresenter {
    private RegisterView mRegisterView;

    public RegisterPresenter(RegisterView registerView) {
        mRegisterView = registerView;
    }

    public void register(User user){
        //显示进度条
        RetrofitClient.getInstance().getAPI().postRequest(user).enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                //隐藏进度条
                mRegisterView.hideProgress();
                if (response.isSuccessful()){
                    UserResult userResult = response.body();
                    if (userResult != null){
                        if (userResult.getErrcode() == 1){
                            //成功，弹Toast
                            mRegisterView.showMessage("注册成功！");
                            return;
                        }
                        //弹Toast
                        mRegisterView.showMessage(userResult.getErrmsg());
                        return;
                    }
                    //弹Toast
                    mRegisterView.showMessage("未知错误");
                    return;
                }
                //弹Toast
                mRegisterView.showMessage("未知错误");
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                //隐藏进度条
                mRegisterView.hideProgress();
                //失败，弹toast
                mRegisterView.showMessage("注册失败");
            }
        });
    }
}
