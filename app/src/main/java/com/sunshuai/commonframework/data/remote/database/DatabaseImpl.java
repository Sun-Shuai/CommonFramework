package com.sunshuai.commonframework.data.remote.database;

/**
 * Created by sunshuai on 2018/4/22
 */
public class DatabaseImpl implements Database {

    private static volatile DatabaseImpl instance;

    private DatabaseImpl() {
    }

    public static DatabaseImpl getInstance() {
        if (instance == null) {
            synchronized (DatabaseImpl.class) {
                if (instance == null) {
                    instance = new DatabaseImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void login(String username, String password, LoginCallBack callBack) {
        if (username.equals("sun") && password.equals("sun")) {
            callBack.onSuccess();
        } else {
            callBack.onFailed();
        }
    }
}
