package com.example.lenovo.myapplication.handler;

import android.content.Context;
import android.os.Message;

import com.example.lenovo.myapplication.base.BaseHandler;

/**
 * Created by lenovo on 2018/4/14.
 */

public class InitDataHandler extends BaseHandler {
    Context context;
    public InitDataHandler(Context context) {
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

    }
}
