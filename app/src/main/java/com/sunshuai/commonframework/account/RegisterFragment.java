package com.sunshuai.commonframework.account;


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
}

