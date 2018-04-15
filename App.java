package com.example.lenovo.myapplication;

import android.app.Application;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lenovo on 2018/4/13.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

    }
}
