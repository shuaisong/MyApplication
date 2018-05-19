package com.example.lenovo.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lenovo on 2018/4/14.
 * auther:lenovo
 * Date：2018/4/14
 */

class SaveFile {
    static void saveFile(Context context, String filename, String tag, String resp) {
        SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(tag, resp);
        edit.apply();
    }
}