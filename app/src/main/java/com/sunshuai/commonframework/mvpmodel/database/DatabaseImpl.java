package com.sunshuai.commonframework.mvpmodel.database;

import com.sunshuai.commonframework.MyApplication;
import com.sunshuai.commonframework.bean.User;
import com.sunshuai.commonframework.bean.User_;

import java.util.List;

import io.objectbox.Box;

/**
 * Created by sunshuai on 2018/4/22
 */
public class DatabaseImpl implements Database {

    private final String TAG = this.getClass().getSimpleName();

    private static volatile DatabaseImpl instance;

    private Box<User> userBox;


    private DatabaseImpl() {
        userBox = MyApplication.getInstance().getBoxStore().boxFor(User.class);
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
    public void login(String username, String password, Callback callBack) {
        List<User> users = userBox.query().equal(User_.username, username).build().find();
        if (users.size() == 0) {
            callBack.onFailed("用户不存在");
        } else {
            if (password.equals(users.get(0).getPassword())) {
                callBack.onSuccess();
            } else {
                callBack.onFailed("账号或密码错误");
            }
        }
    }

    @Override
    public void register(String username, String password, Callback callback) {
        List<User> users = userBox.query().equal(User_.username, username).build().find();
        if (users.size() == 0) {
            userBox.put(new User(username, password));
            callback.onSuccess();
        } else {
            callback.onFailed("用户已存在");
        }
    }


}
