package com.sunshuai.commonframework.account.register;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.sunshuai.commonframework.R;
import com.sunshuai.commonframework.account.login.LoginFragment;
import com.sunshuai.commonframework.base.BaseFragment;
import com.sunshuai.commonframework.utils.KeyboardWatcher;
import com.sunshuai.commonframework.widget.DrawableTextView;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;

public class RegisterFragment extends BaseFragment<RegisterView, RegisterPresenter> implements RegisterView, KeyboardWatcher.SoftKeyboardStateListener {


    private KeyboardWatcher keyboardWatcher;
    private int screenHeight = 0;//屏幕高度
    private float scale = 0.8f; //logo缩放比例


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.logo)
    DrawableTextView logo;
    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.edit_password_again)
    EditText editPasswordAgain;
    @BindView(R.id.pb_loading)
    ProgressBar progressBar;
    @BindView(R.id.body)
    View body;
    @BindView(R.id.iv_clean_phone)
    ImageView ivCleanPhone;
    @BindView(R.id.iv_clean_password)
    ImageView ivCleanPassword;
    @BindView(R.id.iv_clean_password_again)
    ImageView ivCleanPasswordAgain;

    @OnClick({R.id.btn_register, R.id.iv_clean_phone, R.id.iv_clean_password, R.id.iv_clean_password_again})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                // TODO: 2018/4/24 自定义用户名和密码规则
                if (TextUtils.isEmpty(editUsername.getText().toString())) {
                    showToast("用户名不能为空");
                } else {
                    if (editPassword.getText().toString().equals(editPasswordAgain.getText().toString())) {
                        progressBar.setVisibility(View.VISIBLE);
                        getPresenter().register(editUsername.getText().toString(), editPassword.getText().toString());
                    } else {
                        showToast("两次密码输入不一致");
                    }
                }
                break;
            case R.id.iv_clean_phone:
                editUsername.setText("");
                break;
            case R.id.iv_clean_password:
                editPassword.setText("");
                break;
            case R.id.iv_clean_password_again:
                editPasswordAgain.setText("");
                break;
            default:
        }

    }


    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        keyboardWatcher = new KeyboardWatcher(getActivity().findViewById(Window.ID_ANDROID_CONTENT));
        keyboardWatcher.addSoftKeyboardStateListener(this);
        screenHeight = this.getResources().getDisplayMetrics().heightPixels; //获取屏幕高度
    }

    @Override
    protected void initOnCreateView() {
        super.initOnCreateView();
        toolbar.setTitle("注册");
        initToolbarNav(toolbar, true);
        setFragmentAnimator(new DefaultHorizontalAnimator());

        editUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && ivCleanPhone.getVisibility() == View.GONE) {
                    ivCleanPhone.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    ivCleanPhone.setVisibility(View.GONE);
                }
            }
        });
        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && ivCleanPassword.getVisibility() == View.GONE) {
                    ivCleanPassword.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    ivCleanPassword.setVisibility(View.GONE);
                }
                if (s.toString().isEmpty())
                    return;
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    showToast("请输入数字或字母");
                    s.delete(temp.length() - 1, temp.length());
                    editPassword.setSelection(s.length());
                }
            }
        });
        editPasswordAgain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && ivCleanPasswordAgain.getVisibility() == View.GONE) {
                    ivCleanPasswordAgain.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    ivCleanPasswordAgain.setVisibility(View.GONE);
                }
                if (s.toString().isEmpty())
                    return;
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    showToast("请输入数字或字母");
                    s.delete(temp.length() - 1, temp.length());
                    editPassword.setSelection(s.length());
                }
            }
        });
    }

    @Override
    public void registerSuccess() {
        hideSoftInput();
        progressBar.setVisibility(View.GONE);
        showToast("注册成功");
        start(LoginFragment.newInstance(), SINGLETASK);
    }

    @Override
    public void registerFailed(String reason) {
        progressBar.setVisibility(View.GONE);
        showToast("注册失败：" + reason);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        keyboardWatcher.removeSoftKeyboardStateListener(this);
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


    @Override
    public void onSoftKeyboardOpened(int keyboardSize) {
        int[] location = new int[2];
        body.getLocationOnScreen(location); //获取body在屏幕中的坐标,控件左上角
        int x = location[0];
        int y = location[1];
        int bottom = screenHeight - (y + body.getHeight());
        if (keyboardSize > bottom) {
            ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(body, "translationY", 0.0f, -(keyboardSize - bottom));
            mAnimatorTranslateY.setDuration(300);
            mAnimatorTranslateY.setInterpolator(new AccelerateDecelerateInterpolator());
            mAnimatorTranslateY.start();
            zoomIn(logo, keyboardSize - bottom);
        }
    }

    @Override
    public void onSoftKeyboardClosed() {
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(body, "translationY", body.getTranslationY(), 0);
        mAnimatorTranslateY.setDuration(300);
        mAnimatorTranslateY.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimatorTranslateY.start();
        zoomOut(logo);
    }

    public void zoomIn(final View view, float dist) {
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, scale);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, scale);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", 0.0f, -dist);
        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();

    }

    public void zoomOut(final View view) {
        if (view.getTranslationY() == 0) {
            return;
        }
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", scale, 1.0f);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", scale, 1.0f);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), 0);
        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();

    }
}

