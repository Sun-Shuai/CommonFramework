package com.sunshuai.commonframework;

import android.app.Application;

import com.sunshuai.commonframework.bean.MyObjectBox;

import io.objectbox.BoxStore;

/**
 * Created by sunshuai on 2018/4/24
 */
public class MyApplication extends Application {
    private static MyApplication instance;
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        boxStore = MyObjectBox.builder().androidContext(MyApplication.this).build();
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}
