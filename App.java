package com.example.lenovo.myapplication;

import android.app.Application;
import android.support.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by lenovo on 2018/4/13.
 * auther:lenovo
 * Date：2018/4/13
 */

public class App extends Application {
    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return super.isLoggable(priority, tag);
            }
        });
        mApp = this;
    }

    public static App getInstance() {
        return mApp;
    }
}
