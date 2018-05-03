package com.sunshuai.commonframework.main;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.sunshuai.commonframework.MyApplication;
import com.sunshuai.commonframework.mvpmodel.sharedpreferences.SPManager;

/**
 * Created by sunshuai on 2018/5/1
 */
public class MainPresenter extends MvpBasePresenter<MainView> {

    void checkLogin() {
        if (SPManager.getInstance(MyApplication.getInstance().getApplicationContext()).isLogined()) {
            getView().goInfo();
        } else {
            getView().goLogin();
        }
    }

    void logout() {
        SPManager.getInstance(MyApplication.getInstance().getApplicationContext()).setLogined(false);
    }
}
