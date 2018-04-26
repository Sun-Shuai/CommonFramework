package com.sunshuai.commonframework.account.login;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.sunshuai.commonframework.mvpmodel.database.DatabaseManager;

/**
 * Created by sunshuai on 2018/4/24
 */
public class LoginPresenter extends MvpBasePresenter<LoginView> {
    public void login(String username, String password) {
        DatabaseManager.getInstance().login(username, password, new DatabaseManager.Callback() {
            @Override
            public void onSuccess() {
                getView().loginSuccess();
            }

            @Override
            public void onFailed(String reason) {
                getView().loginFailed(reason);
            }
        });
    }
}
