package com.sunshuai.commonframework.mvpmodel.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sunshuai on 2018/4/24
 */
public class SPManager {

    private static final String SP_NAME = "sp_date";


    private static final String KEY_ISFIRSTENTER = "isFirstEnter";
    private static final String KEY_LOGINED_USERNAME = "loginedUsername";


    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
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
        return sp.getBoolean(KEY_ISFIRSTENTER, true);
    }

    public void setFirstEnter(boolean isFirst) {
        editor.putBoolean(KEY_ISFIRSTENTER, isFirst);
        editor.apply();
    }

    public void saveLoginInfo(String username) {
        editor.putString(KEY_LOGINED_USERNAME, username);
        editor.apply();
    }

    public String getLoginInfo() {
        return sp.getString(KEY_LOGINED_USERNAME, "");
    }
}
