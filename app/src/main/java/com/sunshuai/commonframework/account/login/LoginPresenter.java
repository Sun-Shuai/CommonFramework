package com.sunshuai.commonframework.account.login;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.sunshuai.commonframework.MyApplication;
import com.sunshuai.commonframework.mvpmodel.database.DatabaseManager;
import com.sunshuai.commonframework.mvpmodel.sharedpreferences.SPManager;


/**
 * Created by sunshuai on 2018/4/24
 */
public class LoginPresenter extends MvpBasePresenter<LoginView> {
    public void login(final String username, String password) {
        DatabaseManager.getInstance().login(username, password, new DatabaseManager.Callback() {
            @Override
            public void onSuccess() {
                SPManager.getInstance(MyApplication.getInstance().getApplicationContext()).setLogined(true);
                SPManager.getInstance(MyApplication.getInstance().getApplicationContext()).saveLoginUsername(username);
                String iconPath = DatabaseManager.getInstance().getIcon(username);
                getView().loginSuccess(iconPath);
            }

            @Override
            public void onFailed(String reason) {
                getView().loginFailed(reason);
            }
        });
    }
}
