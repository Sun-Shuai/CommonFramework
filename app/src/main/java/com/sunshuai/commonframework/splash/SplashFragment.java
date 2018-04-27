package com.sunshuai.commonframework.splash;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.sunshuai.commonframework.R;
import com.sunshuai.commonframework.account.login.LoginFragment;
import com.sunshuai.commonframework.base.BaseFragment;
import com.sunshuai.commonframework.home.HomeFragment;

public class SplashFragment extends BaseFragment<SplashView, SplashPresenter> implements SplashView {

    private static final long delayMills = 3000;

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getPresenter().checkLogin();
            }
        }, delayMills);
    }

    @Override
    public void loadUserInfo(String username) {
        TextView tvUsername = getActivity().findViewById(R.id.tv_name);
        tvUsername.setText(username);
        startWithPop(HomeFragment.newInstance());
    }

    @Override
    public void goLogin() {
        startWithPop(LoginFragment.newInstance());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @NonNull
    @Override
    public SplashPresenter createPresenter() {
        return new SplashPresenter();
    }
}
