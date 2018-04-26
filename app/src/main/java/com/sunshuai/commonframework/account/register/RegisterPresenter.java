package com.sunshuai.commonframework.account.register;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.sunshuai.commonframework.mvpmodel.database.DatabaseManager;

/**
 * Created by sunshuai on 2018/4/24
 */
public class RegisterPresenter extends MvpBasePresenter<RegisterView> {

    public void register(String username,String password){
        DatabaseManager.getInstance().register(username, password, new DatabaseManager.Callback() {
            @Override
            public void onSuccess() {
                getView().registerSuccess();
            }

            @Override
            public void onFailed(String reason) {
                getView().registerFailed(reason);
            }
        });
    }
}
