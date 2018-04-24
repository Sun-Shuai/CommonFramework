package com.sunshuai.commonframework.account.login;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by sunshuai on 2018/4/24
 */
public interface LoginView extends MvpView {
    void showLoading();

    void hideLoading();

    void showSuccessMsg();

    void showFailedMsg();
}
