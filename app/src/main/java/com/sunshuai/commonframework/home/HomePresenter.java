package com.sunshuai.commonframework.home;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.sunshuai.commonframework.MyApplication;
import com.sunshuai.commonframework.mvpmodel.sharedpreferences.SPDataImpl;

/**
 * Created by sunshuai on 2018/4/24
 */
public class HomePresenter extends MvpBasePresenter<HomeView> {

    void checkIsFirst() {
        if (SPDataImpl.getInstance(MyApplication.getInstance().getApplicationContext()).isFirstEnter()) {
            getView().showGuidePage();
            SPDataImpl.getInstance(MyApplication.getInstance().getApplicationContext()).setFirstEnter(false);
        }
    }
}
