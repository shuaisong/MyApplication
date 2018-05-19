package com.example.lenovo.myapplication.base;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.lenovo.myapplication.App;

import java.lang.ref.WeakReference;

/**
 * Created by lenovo on 2018/4/14.
 */

public class BaseHandler extends Handler {
    public final static int NO_NET_CONN = 199;


    @Override
    public void handleMessage(Message msg) {
        if (msg.arg1 == NO_NET_CONN) {
            Toast.makeText(App.getInstance(), "无网络连接", Toast.LENGTH_SHORT).show();
        }

    }
}
