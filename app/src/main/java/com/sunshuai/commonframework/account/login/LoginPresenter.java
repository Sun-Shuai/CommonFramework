package com.sunshuai.commonframework.account.login;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.sunshuai.commonframework.data.remote.database.Database;
import com.sunshuai.commonframework.data.remote.database.DatabaseImpl;

/**
 * Created by sunshuai on 2018/4/24
 */
public class LoginPresenter extends MvpBasePresenter<LoginView> {
    public void login(String username, String password) {
        getView().showLoading();
        DatabaseImpl.getInstance().login(username, password, new Database.LoginCallBack() {
            @Override
            public void onSuccess() {
                getView().hideLoading();
                getView().showSuccessMsg();
            }

            @Override
            public void onFailed() {
                getView().hideLoading();
                getView().showFailedMsg();
            }
        });
    }
}
