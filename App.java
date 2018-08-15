package com.example.lenovo.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.StrictMode;
import android.util.Log;

import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.component.DaggerAppComponent;
import com.example.lenovo.myapplication.module.AppModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import static android.content.ContentValues.TAG;

/**
 * Created by lenovo on 2018/4/13.
 * auther:lenovo
 * Date：2018/4/13
 */

public class App extends Application {
    private static App mApp;
    private RefWatcher refWatcher;
    private AppComponent appComponent;

    private boolean isDebug() {
        ApplicationInfo mInfo = getApplicationInfo();
        return (mInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: " + isDebug());
        if (isDebug()) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }

        mApp = this;
        refWatcher = stepLeakCanary();
        initCompoent();
    }

    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    private RefWatcher stepLeakCanary() {
        return LeakCanary.install(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static RefWatcher getRefWatcher(Context context) {
        App mApp = (App) context.getApplicationContext();
        return mApp.refWatcher;
    }

    public static App getInstance() {
        return mApp;
    }
}
