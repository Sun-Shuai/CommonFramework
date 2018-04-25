package com.sunshuai.commonframework.splash;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunshuai.commonframework.R;
import com.sunshuai.commonframework.base.BaseFragment;
import com.sunshuai.commonframework.home.HomeFragment;

public class SplashFragment extends BaseFragment<SplashView, SplashPresenter> implements SplashView {

    private static final long delayMills = 3000;

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                replaceFragment(HomeFragment.newInstance(), false);
            }
        }, delayMills);
        return super.onCreateView(inflater, container, savedInstanceState);
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
