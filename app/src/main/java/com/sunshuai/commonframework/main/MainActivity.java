package com.sunshuai.commonframework.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.sunshuai.commonframework.R;
import com.sunshuai.commonframework.account.information.InfoFragment;
import com.sunshuai.commonframework.account.login.LoginFragment;
import com.sunshuai.commonframework.base.BaseActivity;
import com.sunshuai.commonframework.base.BaseFragment;
import com.sunshuai.commonframework.home.HomeFragment;
import com.sunshuai.commonframework.splash.SplashFragment;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

import static me.yokeyword.fragmentation.ISupportFragment.SINGLETASK;

/**
 * Created by sunshuai on 2018/4/24
 */

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView, NavigationView.OnNavigationItemSelectedListener, BaseFragment.OnFragmentOpenDrawerListener {

    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ISupportFragment fragment = findFragment(SplashFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.fl_container, SplashFragment.newInstance());
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(MainActivity.this);
        navigationView.setCheckedItem(R.id.nav_home);

        // 隐藏NavigationView的滚动条
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }

        LinearLayout llNavHeader = (LinearLayout) navigationView.getHeaderView(0);
        llNavHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(GravityCompat.START);
                drawerLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getPresenter().checkLogin();
                    }
                }, 250);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressedSupport() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            ISupportFragment topFragment = getTopFragment();

            if (topFragment instanceof BaseFragment) {
                navigationView.setCheckedItem(R.id.nav_home);
            }

            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                pop();
            } else {
                if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                    finish();
                } else {
                    TOUCH_TIME = System.currentTimeMillis();
                    showToast("再次点击退出");
                }
            }
        }

    }


    @Override
    public void goLogin() {
        start(LoginFragment.newInstance(), SINGLETASK);
    }

    @Override
    public void goInfo() {
        start(InfoFragment.newInstance(), SINGLETASK);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        drawerLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                final ISupportFragment topFragment = getTopFragment();
                BaseFragment myHome = (BaseFragment) topFragment;

                switch (item.getItemId()) {

                    case R.id.nav_home:
                        HomeFragment fragment = findFragment(HomeFragment.class);
                        Bundle newBundle = new Bundle();
                        newBundle.putString("from", "From:" + topFragment.getClass().getSimpleName());
                        fragment.putNewBundle(newBundle);
                        myHome.start(fragment, SINGLETASK);
                        break;

                    case R.id.nav_logout:
                        // TODO: 2018/5/2 清除登录缓存
                        break;

                    default:
                }
            }
        }, 300);

        return true;
    }

    @Override
    public void onOpenDrawer() {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

}
