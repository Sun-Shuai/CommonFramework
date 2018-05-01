package com.sunshuai.commonframework.account.information;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.sunshuai.commonframework.MyApplication;
import com.sunshuai.commonframework.R;
import com.sunshuai.commonframework.base.BaseFragment;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class InfoFragment extends BaseFragment<InfoView, InfoPresenter> implements InfoView {

    private final int REQUEST_CODE_SECECT_PHOTO = 1;

    private CircleImageView circleImageView;

    @BindView(R.id.civ_user_icon)
    CircleImageView cIVUserIcon;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @OnClick({R.id.civ_user_icon})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.civ_user_icon:
                selectPhoto();
                break;

            default:
        }
    }

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @Override
    protected void initView() {
        super.initView();
        toolbar.setTitle("个人信息");
        initToolbarNav(toolbar, true);
        circleImageView = getActivity().findViewById(R.id.img_nav);
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().loadUserIcon();
    }

    private void selectPhoto() {
        MultiImageSelector.create(getActivity())
                .showCamera(true)
                .single()
                .start(this, REQUEST_CODE_SECECT_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SECECT_PHOTO && resultCode == RESULT_OK) {
            //选择图片返回
            List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            Logger.e(path.get(0));
            UCrop.Options options = new UCrop.Options();
            options.setHideBottomControls(true);
            options.setToolbarColor(getResources().getColor(R.color.colorPrimary));
            options.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
            Uri destination = Uri.fromFile(new File(getActivity().getExternalCacheDir(), System.currentTimeMillis() + ".jpg"));
            UCrop.of(Uri.fromFile(new File(path.get(0))), destination)
                    .withAspectRatio(1, 1)
                    .withOptions(options)
                    .start(getActivity(), this);
        }
        if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
            final Uri resultUri = UCrop.getOutput(data);
            Glide.with(this).load(resultUri).into(cIVUserIcon);     //图片生命周期为fragment
            Glide.with(MyApplication.getInstance().getApplicationContext()).load(resultUri).into(circleImageView);    //图片生命周期到应用结束
            getPresenter().saveUserIcon(resultUri.getPath());
        }

    }


    @Override
    public void loadUserIcon(String iconPath) {
        Glide.with(this).load(iconPath).into(cIVUserIcon);
    }


    @Override
    public InfoPresenter createPresenter() {
        return new InfoPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info;
    }


}
