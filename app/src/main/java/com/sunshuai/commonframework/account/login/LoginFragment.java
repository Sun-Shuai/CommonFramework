package com.sunshuai.commonframework.account.login;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.sunshuai.commonframework.MyApplication;
import com.sunshuai.commonframework.R;
import com.sunshuai.commonframework.account.register.RegisterFragment;
import com.sunshuai.commonframework.base.BaseFragment;
import com.sunshuai.commonframework.home.HomeFragment;
import com.sunshuai.commonframework.utils.KeyboardWatcher;
import com.sunshuai.commonframework.widget.DrawableTextView;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.yokeyword.fragmentation.SupportHelper;

public class LoginFragment extends BaseFragment<LoginView, LoginPresenter> implements LoginView, KeyboardWatcher.SoftKeyboardStateListener {


    private KeyboardWatcher keyboardWatcher;
    private int screenHeight = 0;//屏幕高度
    private float scale = 0.8f; //logo缩放比例


    private TextView tvUsername;

    @BindView(R.id.logo)
    DrawableTextView logo;
    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.pb_loading)
    ProgressBar progressBar;
    @BindView(R.id.iv_clean_phone)
    ImageView ivCleanPhone;
    @BindView(R.id.iv_clean_password)
    ImageView ivCleanPassword;
    @BindView(R.id.body)
    View body;

    @OnClick({R.id.btn_login, R.id.txt_regist, R.id.close, R.id.iv_clean_phone, R.id.iv_clean_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (TextUtils.isEmpty(editUsername.getText().toString())) {
                    showToast("用户名不能为空");
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    getPresenter().login(editUsername.getText().toString(), editPassword.getText().toString());
                }
                break;
            case R.id.txt_regist:
                start(RegisterFragment.newInstance(), SINGLETASK);
                break;
            case R.id.iv_clean_phone:
                editUsername.setText("");
                break;
            case R.id.iv_clean_password:
                editPassword.setText("");
                break;
            case R.id.close:
                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() == 1) {
                    startWithPop(HomeFragment.newInstance());
                } else {
                    pop();
                }
                break;

            default:
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        keyboardWatcher = new KeyboardWatcher(getActivity().findViewById(Window.ID_ANDROID_CONTENT));
        keyboardWatcher.addSoftKeyboardStateListener(this);
        screenHeight = this.getResources().getDisplayMetrics().heightPixels; //获取屏幕高度
        tvUsername = getActivity().findViewById(R.id.tv_name);
    }

    @Override
    protected void initOnCreateView() {
        super.initOnCreateView();
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
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void loginSuccess(String iconPath) {
        hideSoftInput();
        showToast("登录成功");
        tvUsername.setText(editUsername.getText().toString());
        if (iconPath != null) {
            Glide.with(getActivity().getApplicationContext()).load(iconPath).into((CircleImageView) (getActivity().findViewById(R.id.civ_user_icon)));
        }
        progressBar.setVisibility(View.GONE);
        HomeFragment fragment = SupportHelper.findFragment(getFragmentManager(), HomeFragment.class);
        if (fragment == null) {
            startWithPop(HomeFragment.newInstance());
        } else {
            start(HomeFragment.newInstance(), SINGLETASK);
        }
    }

    @Override
    public void loginFailed(String reason) {
        progressBar.setVisibility(View.GONE);
        showToast("登录失败：" + reason);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        keyboardWatcher.removeSoftKeyboardStateListener(this);
    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
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

    // TODO: 2018/5/2 适配不同分辨率

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
