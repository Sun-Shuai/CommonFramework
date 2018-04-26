package com.sunshuai.commonframework.account.register;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by sunshuai on 2018/4/24
 */
public interface RegisterView extends MvpView {

    void registerSuccess();

    void registerFailed(String reason);
}
