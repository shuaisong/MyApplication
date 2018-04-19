package com.example.lenovo.myapplication.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;

import com.example.lenovo.myapplication.MainActivity;
import com.example.lenovo.myapplication.base.BaseHandler;

import java.io.IOException;

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
        OkHttpClient client = new OkHttpClient();

        final Request request = BaseRequest.newRequest().url("http://mmapi.yomei.tv/mm131/initData")
                .addHeader("sign", "116F79E7671A95F77A1750FBEA88A124").build();
        final Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

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