package com.example.lenovo.myapplication.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.service.chooser.ChooserTargetService;

import com.orhanobut.logger.Logger;

/**
 * Created by lenovo on 2018/4/26.
 * auther:lenovo
 * Date：2018/4/26
 */

public class NetBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.d("网络");
    }
}
