package com.sunshuai.commonframework.mvpmodel.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sunshuai on 2018/4/24
 */
public class SPManager {

    private static final String SP_NAME = "sp_date";
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private static volatile SPManager instance;

    private SPManager(Context context) {
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();

    }

    public static SPManager getInstance(Context context) {
        if (instance == null) {
            synchronized (SPManager.class) {
                if (instance == null) {
                    instance = new SPManager(context);
                }
            }
        }
        return instance;
    }


    public boolean isFirstEnter() {
        return sp.getBoolean("isFirstEnter", true);
    }

    public void setFirstEnter(boolean isFirst) {
        editor.putBoolean("isFirstEnter", isFirst);
        editor.apply();
    }
}
