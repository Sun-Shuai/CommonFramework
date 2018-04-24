package com.sunshuai.commonframework.account.register;


import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.sunshuai.commonframework.R;
import com.sunshuai.commonframework.base.BaseFragment;

public class RegisterFragment extends BaseFragment {

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @NonNull
    @Override
    public MvpPresenter createPresenter() {
        return new MvpBasePresenter();
    }
}

