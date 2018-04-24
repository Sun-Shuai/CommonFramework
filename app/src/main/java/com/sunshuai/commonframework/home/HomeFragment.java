package com.sunshuai.commonframework.home;


import com.sunshuai.commonframework.R;
import com.sunshuai.commonframework.account.LoginFragment;
import com.sunshuai.commonframework.base.BaseFragment;

import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    @OnClick(R.id.btn_login)
    public void onClick() {
        start(LoginFragment.newInstance());
    }


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
}
