package com.example.lenovo.myapplication.BroadcastReceiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.example.lenovo.myapplication.utils.LogUtil;
import com.example.lenovo.myapplication.utils.Util;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.
        String mAction = intent.getAction();
        int mType = intent.getIntExtra("type", -1);

        if (mType != -1) {
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (mNotificationManager != null) {
                mNotificationManager.cancel(mType);
            }
        }
        if ("notification_clicked".equals(mAction)) {
            LogUtil.d("notification_clicked");
            String mFilePath = intent.getStringExtra("filePath");
            if (!TextUtils.isEmpty(mFilePath))
                Util.instanllAPK(context,mFilePath);
        }
//            Util.instanllAPK();
        if ("notification_cancelled".equals(mAction)) {
            LogUtil.d("notification_cancelled");
        }
    }

}
