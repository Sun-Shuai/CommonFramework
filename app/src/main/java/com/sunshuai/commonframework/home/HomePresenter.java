package com.sunshuai.commonframework.home;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.sunshuai.commonframework.MyApplication;
import com.sunshuai.commonframework.mvpmodel.sharedpreferences.SPManager;

/**
 * Created by sunshuai on 2018/4/24
 */
public class HomePresenter extends MvpBasePresenter<HomeView> {

    void checkIsFirst() {
        if (SPManager.getInstance(MyApplication.getInstance().getApplicationContext()).isFirstEnter()) {
            getView().showGuidePage();
            SPManager.getInstance(MyApplication.getInstance().getApplicationContext()).setFirstEnter(false);
        }
    }
}
