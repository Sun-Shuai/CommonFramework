package com.sunshuai.commonframework.splash;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.sunshuai.commonframework.MyApplication;
import com.sunshuai.commonframework.mvpmodel.database.DatabaseManager;
import com.sunshuai.commonframework.mvpmodel.sharedpreferences.SPManager;

/**
 * Created by sunshuai on 2018/4/25
 */
public class SplashPresenter extends MvpBasePresenter<SplashView> {

    void checkLogin() {
        if (SPManager.getInstance(MyApplication.getInstance().getApplicationContext()).isLogined()){
            String username = SPManager.getInstance(MyApplication.getInstance().getApplicationContext()).getLoginUsername();
            getView().loadUsername(username);
            String iconPath = DatabaseManager.getInstance().getIcon(username);
            if (iconPath != null) {
                getView().loadUserIcon(iconPath);
            }
        } else {
            getView().goLogin();
        }
    }
}
