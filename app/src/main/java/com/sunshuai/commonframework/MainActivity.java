package com.sunshuai.commonframework;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.sunshuai.commonframework.account.login.LoginFragment;
import com.sunshuai.commonframework.base.BaseActivity;
import com.sunshuai.commonframework.base.BaseFragment;
import com.sunshuai.commonframework.home.HomeFragment;
import com.sunshuai.commonframework.splash.SplashFragment;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Created by sunshuai on 2018/4/24
 */

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BaseFragment.OnFragmentOpenDrawerListener {

    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;


    private TextView txtName;
    private ImageView imageIcon;

    @Override
    protected void initView() {
        super.initView();
        ISupportFragment fragment = findFragment(SplashFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.fl_container, SplashFragment.newInstance());
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(MainActivity.this);
        navigationView.setCheckedItem(R.id.nav_home);

        LinearLayout llNavHeader = (LinearLayout) navigationView.getHeaderView(0);
        txtName = llNavHeader.findViewById(R.id.tv_name);
        imageIcon = llNavHeader.findViewById(R.id.img_nav);
        llNavHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(GravityCompat.START);
                drawerLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        start(LoginFragment.newInstance());
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


    @NonNull
    @Override
    public MvpPresenter createPresenter() {
        return new MvpBasePresenter();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        drawerLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                int id = item.getItemId();

                final ISupportFragment topFragment = getTopFragment();
                BaseFragment myHome = (BaseFragment) topFragment;

                if (id == R.id.nav_home) {
                    HomeFragment fragment = (HomeFragment) findFragment(HomeFragment.class);
                    Bundle newBundle = new Bundle();
                    newBundle.putString("from", "From:" + topFragment.getClass().getSimpleName());
                    fragment.putNewBundle(newBundle);
                    myHome.start(fragment, ISupportFragment.SINGLETASK);
//                } else if (id == R.id.nav_discover) {
//                    DiscoverFragment fragment = findFragment(DiscoverFragment.class);
//                    if (fragment == null) {
//                        myHome.startWithPopTo(DiscoverFragment.newInstance(), HomeFragment.class, false);
//                    } else {
//                        // 如果已经在栈内,则以SingleTask模式start
//                        myHome.start(fragment, SupportFragment.SINGLETASK);
//                    }
//                } else if (id == R.id.nav_shop) {
//                    ShopFragment fragment = findFragment(ShopFragment.class);
//                    if (fragment == null) {
//                        myHome.startWithPopTo(ShopFragment.newInstance(), HomeFragment.class, false);
//                    } else {
//                        // 如果已经在栈内,则以SingleTask模式start,也可以用popTo
////                        start(fragment, SupportFragment.SINGLETASK);
//                        myHome.popTo(ShopFragment.class, false);
//                    }
                } else if (id == R.id.nav_login) {
                    start(LoginFragment.newInstance());
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
}
