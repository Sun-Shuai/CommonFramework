package com.sunshuai.commonframework;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
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
        initObjectBox();
        initLogger();
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }

    private void initObjectBox() {
        boxStore = MyObjectBox.builder().androidContext(MyApplication.this).build();
    }


    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter(
                PrettyFormatStrategy
                        .newBuilder()
                        .tag("Logger")
                        .build())
        );
    }
}
