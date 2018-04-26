package com.sunshuai.commonframework.home;


import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sunshuai.commonframework.R;
import com.sunshuai.commonframework.base.BaseFragment;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView, Toolbar.OnMenuItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    protected void initView() {
        super.initView();

        toolbar.setTitle("主界面");
        initToolbarNav(toolbar, false);
        toolbar.inflateMenu(R.menu.home);
        toolbar.setOnMenuItemClickListener(this);


    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @NonNull
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                final PopupMenu popupMenu = new PopupMenu(fragmentActivity, toolbar, GravityCompat.END);
                popupMenu.inflate(R.menu.home_pop);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action0:
                                showToast("1");
                                break;
                            case R.id.action1:
                                showToast("2");
                                break;
                            case R.id.action2:
                                showToast("3");
                                break;
                        }
                        popupMenu.dismiss();
                        return true;
                    }
                });
                popupMenu.show();
                break;
        }
        return true;
    }

}
