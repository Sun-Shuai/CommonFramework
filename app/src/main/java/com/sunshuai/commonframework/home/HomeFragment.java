package com.sunshuai.commonframework.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.sunshuai.commonframework.R;
import com.sunshuai.commonframework.account.login.LoginFragment;
import com.sunshuai.commonframework.base.BaseFragment;

import butterknife.OnClick;

public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {

    @OnClick(R.id.btn_goLogin)
    public void onClick() {
        start(LoginFragment.newInstance());
    }


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPresenter().checkIsFirst();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @NonNull
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void showGuidePage() {
        showToast("first");
        // TODO: 2018/4/24 start guideFragment
    }
}
