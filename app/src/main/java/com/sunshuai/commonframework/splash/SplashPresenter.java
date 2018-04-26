package com.sunshuai.commonframework.splash;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.sunshuai.commonframework.MyApplication;
import com.sunshuai.commonframework.mvpmodel.sharedpreferences.SPManager;

/**
 * Created by sunshuai on 2018/4/25
 */
public class SplashPresenter extends MvpBasePresenter<SplashView> {

    void checkLogin() {
        String username = SPManager.getInstance(MyApplication.getInstance().getApplicationContext()).getLoginInfo();
        if (username.equals("")) {
            getView().goLogin();
        } else {
            getView().loadUserInfo(username);
        }
    }
}
