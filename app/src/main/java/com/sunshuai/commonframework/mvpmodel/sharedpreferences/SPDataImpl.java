package com.sunshuai.commonframework.mvpmodel.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sunshuai on 2018/4/24
 */
public class SPDataImpl implements SPData {

    private static final String SP_NAME = "sp_date";
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private static volatile SPDataImpl instance;

    private SPDataImpl(Context context) {
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();

    }

    public static SPDataImpl getInstance(Context context) {
        if (instance == null) {
            synchronized (SPDataImpl.class) {
                if (instance == null) {
                    instance = new SPDataImpl(context);
                }
            }
        }
        return instance;
    }


    @Override
    public boolean isFirstEnter() {
        return sp.getBoolean("isFirstEnter", true);
    }

    @Override
    public void setFirstEnter(boolean isFirst) {
        editor.putBoolean("isFirstEnter", isFirst);
        editor.apply();
    }
}
