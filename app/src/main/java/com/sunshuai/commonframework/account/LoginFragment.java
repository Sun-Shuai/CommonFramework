package com.sunshuai.commonframework.account;


import com.sunshuai.commonframework.R;
import com.sunshuai.commonframework.base.BaseFragment;

import butterknife.OnClick;

public class LoginFragment extends BaseFragment {


    @OnClick(R.id.btn_register)
    public void onClick() {
        start(RegisterFragment.newInstance());
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }
}
