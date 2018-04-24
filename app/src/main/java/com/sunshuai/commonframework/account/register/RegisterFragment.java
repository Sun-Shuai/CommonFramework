package com.sunshuai.commonframework.account.register;


import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.sunshuai.commonframework.R;
import com.sunshuai.commonframework.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment<RegisterView, RegisterPresenter> implements RegisterView {


    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_password1)
    EditText editPassword1;
    @BindView(R.id.edit_password2)
    EditText editPassword2;
    @BindView(R.id.pb_loading)
    ProgressBar progressBar;

    @OnClick(R.id.btn_Register)
    public void onClick() {
        // TODO: 2018/4/24 自定义用户名和密码规则
        if (TextUtils.isEmpty(editUsername.getText().toString())) {
            showToast("用户名不能为空");
        } else {
            if (editPassword1.getText().toString().equals(editPassword2.getText().toString())) {
                getPresenter().register(editUsername.getText().toString(), editPassword1.getText().toString());
            } else {
                showToast("两次密码输入不一致");
            }
        }
    }


    public static RegisterFragment newInstance() {
        return new RegisterFragment();
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
        showToast("注册成功");
    }

    @Override
    public void showFailedMsg(String reason) {
        showToast("注册失败：" + reason);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @NonNull
    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }
}

