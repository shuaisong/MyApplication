package com.example.lenovo.myapplication.component;

import android.content.Context;

import com.example.lenovo.myapplication.module.AppModule;

import dagger.Component;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */
@Component(modules = AppModule.class)
public interface AppComponent {
    Context getContext();
}
