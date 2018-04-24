package com.sunshuai.commonframework.account.register;


import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

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
            Toast.makeText(getActivity(), "用户名不能为空", Toast.LENGTH_SHORT).show();
        } else {
            if (editPassword1.getText().toString().equals(editPassword2.getText().toString())) {
                getPresenter().register(editUsername.getText().toString(), editPassword1.getText().toString());
            } else {
                Toast.makeText(getActivity(), "两次密码输入不一致", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedMsg(String reason) {
        Toast.makeText(getActivity(), "注册失败：" + reason, Toast.LENGTH_SHORT).show();
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

