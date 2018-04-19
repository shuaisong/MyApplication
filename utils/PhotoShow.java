package com.example.lenovo.myapplication.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.example.lenovo.myapplication.MainActivity;
import com.example.lenovo.myapplication.base.BaseHandler;

import java.io.IOException;
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

public class PhotoShow {
    private static final String TAG = "Tag";
    public static final String FRESH_SIGN = "20B140BADA51CE0DECE7ACFC56A672C0";
    public static final String LOAD_SIGN = "900351F69CE748843CFC46C8702BB0BB";
    public static final String LOAD_SIGN_NEW = "11DE3F7E603E8743D6AD368C28AD4348";

    public static void showPhoto(final Context context, final BaseHandler handler, String url, String sign) {
        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(10, TimeUnit.SECONDS).
                readTimeout(20, TimeUnit.SECONDS).
                build();
        final Request request = BaseRequest.newRequest().url(url)
                .addHeader("sign", sign).build();
        final Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.code());
                String string = response.body().string();
                Log.d(TAG, "run: " + string);
                Log.d(TAG, "run: response.code() " + response.code());
                SaveFile.saveFile(context, "showPhoto", "showPhoto", string);
                Message message = handler.obtainMessage();
                message.what = response.code();
                Bundle bundle = new Bundle();
                bundle.putString("showPhoto", string);
                message.setData(bundle);
                handler.sendMessage(message);
                Log.d(TAG, "run: sendMessage");
            }
        });

    }
}
