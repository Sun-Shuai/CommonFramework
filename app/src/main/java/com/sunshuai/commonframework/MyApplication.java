package com.sunshuai.commonframework;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.LeakCanary;
import com.sunshuai.commonframework.bean.MyObjectBox;

import io.objectbox.BoxStore;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

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
        initStackView();
        initLeakCanary();
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


    private void initStackView() {
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                    }
                })
                .install();
    }


    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter(
                PrettyFormatStrategy
                        .newBuilder()
                        .tag("Logger")
                        .build())
        );
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
