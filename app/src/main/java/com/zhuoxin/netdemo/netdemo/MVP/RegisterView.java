package com.zhuoxin.netdemo.netdemo.MVP;

/**
 * Created by Dionysus on 2017/8/29.
 */

public interface RegisterView {
    void showProgress();//展示进度条
    void hideProgress();//隐藏进度条
    void showMessage(String msg);
}
