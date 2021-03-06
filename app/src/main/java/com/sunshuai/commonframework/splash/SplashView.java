package com.sunshuai.commonframework.splash;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by sunshuai on 2018/4/25
 */
public interface SplashView extends MvpView {

    void goLogin();

    void loadUsername(String username);

    void loadUserIcon(String iconPath);

}
