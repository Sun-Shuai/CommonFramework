package com.sunshuai.commonframework.data.remote.database;

/**
 * Created by sunshuai on 2018/4/22
 */
public interface Database {
    void login(String username, String password, LoginCallBack callBack);

    interface LoginCallBack {
        void onSuccess();

        void onFailed();
    }
}
