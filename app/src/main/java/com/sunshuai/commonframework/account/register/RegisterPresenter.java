package com.sunshuai.commonframework.account.register;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.sunshuai.commonframework.data.local.database.Database;
import com.sunshuai.commonframework.data.local.database.DatabaseImpl;

/**
 * Created by sunshuai on 2018/4/24
 */
public class RegisterPresenter extends MvpBasePresenter<RegisterView> {

    public void register(String username,String password){
        getView().showLoading();
        DatabaseImpl.getInstance().register(username, password, new Database.Callback() {
            @Override
            public void onSuccess() {
                getView().hideLoading();
                getView().showSuccessMsg();
            }

            @Override
            public void onFailed(String reason) {
                getView().hideLoading();
                getView().showFailedMsg(reason);
            }
        });
    }
}
