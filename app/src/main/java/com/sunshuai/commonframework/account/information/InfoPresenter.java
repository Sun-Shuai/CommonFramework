package com.sunshuai.commonframework.account.information;


import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.sunshuai.commonframework.MyApplication;
import com.sunshuai.commonframework.mvpmodel.database.DatabaseManager;
import com.sunshuai.commonframework.mvpmodel.sharedpreferences.SPManager;


/**
 * Created by sunshuai on 2018/5/1
 */
public class InfoPresenter extends MvpBasePresenter<InfoView> {

    public void loadUserIcon() {
        String username = SPManager.getInstance(MyApplication.getInstance().getApplicationContext()).getLoginUsername();
        String iconPath = DatabaseManager.getInstance().getIcon(username);
        if (iconPath != null) {
            getView().loadUserIcon(iconPath);
        }
    }

    public void saveUserIcon(String path) {
        String username = SPManager.getInstance(MyApplication.getInstance().getApplicationContext()).getLoginUsername();
        DatabaseManager.getInstance().saveIcon(username, path);
    }
}
