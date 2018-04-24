package com.sunshuai.commonframework.mvpmodel.database;

/**
 * Created by sunshuai on 2018/4/22
 */
public interface Database {
    void login(String username, String password, Callback callBack);

    void register(String username,String password,Callback callback);

    interface Callback {
        void onSuccess();

        void onFailed(String reason);
    }
}
