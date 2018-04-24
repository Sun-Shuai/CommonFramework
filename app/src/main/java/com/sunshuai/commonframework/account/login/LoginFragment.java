package com.sunshuai.commonframework.account.login;


import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sunshuai.commonframework.R;
import com.sunshuai.commonframework.account.register.RegisterFragment;
import com.sunshuai.commonframework.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment<LoginView, LoginPresenter> implements LoginView {

    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.pb_loading)
    ProgressBar progressBar;

    @OnClick({R.id.btn_login, R.id.btn_goRegister})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                getPresenter().login(editUsername.getText().toString(), editPassword.getText().toString());
                break;
            case R.id.btn_goRegister:
                start(RegisterFragment.newInstance());
                break;
        }
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showSuccessMsg() {
        Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedMsg() {
        Toast.makeText(getActivity(), "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }


    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }
}