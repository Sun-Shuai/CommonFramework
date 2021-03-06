package com.sunshuai.commonframework.account.information;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.Glide;
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
    CircleImageView cIvUserIcon;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @OnClick({R.id.civ_user_icon})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.civ_user_icon:
                // TODO: 2018/5/3 头像预览页 
                selectPhoto();
                break;

            default:
        }
    }

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }


    @Override
    protected void initOnCreateView() {
        super.initOnCreateView();
        toolbar.setTitle("个人信息");
        initToolbarNav(toolbar, true);
        circleImageView = getActivity().findViewById(R.id.civ_user_icon);
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
            List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            UCrop.Options options = new UCrop.Options();
            options.setHideBottomControls(true);
            options.setToolbarColor(getResources().getColor(R.color.colorPrimary));
            options.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
            Uri destination = Uri.fromFile(new File(getActivity().getExternalCacheDir(), System.currentTimeMillis() + ".jpg"));
            UCrop.of(Uri.fromFile(new File(path.get(0))), destination)
                    .withAspectRatio(1, 1)
                    .withOptions(options)
                    .start(getActivity(), this);
        }
        if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
            final Uri resultUri = UCrop.getOutput(data);
            Glide.with(this).load(resultUri).into(cIvUserIcon);     //图片生命周期为fragment
            Glide.with(getActivity().getApplicationContext()).load(resultUri).into(circleImageView);    //图片生命周期到应用结束
            getPresenter().saveUserIcon(resultUri.getPath());
        }

    }


    @Override
    public void loadUserIcon(String iconPath) {
        Glide.with(this).load(iconPath).into(cIvUserIcon);
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
