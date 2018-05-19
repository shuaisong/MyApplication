package com.example.lenovo.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Message;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.lenovo.myapplication.MainActivity;
import com.example.lenovo.myapplication.base.BaseHandler;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by lenovo on 2018/4/14.
 */

public class Initdata {
    private static final String TAG = "Tag";

    public static void init(final Context context, final BaseHandler handler) {
        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS).build();
        final Request request = BaseRequest.newRequest().url("http://mmapi.yomei.tv/mm131/initData")
                .addHeader("sign", "116F79E7671A95F77A1750FBEA88A124").build();
        final Call call = client.newCall(request);
        call.enqueue(new Callback() {
            int serversLoadTimes = 0;

            @Override
            public void onFailure(Call call, IOException e) {
                if (e.getCause() == null) {
                    handler.sendEmptyMessage(BaseHandler.NO_NET_CONN);
                } else {
                    if ((e.getCause()).equals(SocketTimeoutException.class) && serversLoadTimes < 3) {
                        serversLoadTimes++;
                        client.newCall(call.request()).enqueue(this);
                    } else {
                        Logger.d(e.getMessage());
                        handler.sendEmptyMessage(BaseHandler.NO_NET_CONN);
                    }
                }

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                String string = body.string();
                SaveFile.saveFile(context, "initData", "initData", string);
                Message message = handler.obtainMessage();
                message.what = response.code();
                Bundle bundle = new Bundle();
                bundle.putString("initData", string);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        });
    }
}