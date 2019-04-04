package com.example.lenovo.myapplication.utils;

import android.widget.Toast;

import com.example.lenovo.myapplication.App;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class ToastUtil {
    public static void show(String msg) {
        Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }
}
