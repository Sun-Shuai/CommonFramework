package com.sunshuai.commonframework.account.information;


import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by sunshuai on 2018/5/1
 */
public interface InfoView extends MvpView {

    void loadUserIcon(String iconPath);

}
