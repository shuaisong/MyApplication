package com.example.lenovo.myapplication.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.example.lenovo.myapplication.MainActivity;
import com.example.lenovo.myapplication.base.BaseHandler;

import java.io.IOException;

import okhttp3.Call;
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
    public static final String LOAD_SIGN = "2F0172811EC489C7C4ADE6645B202B69";

    public static void showPhoto(final Context context, final BaseHandler handler, String url, String sign) {
//        Log.d(TAG, "showPhoto: ");
        OkHttpClient client = new OkHttpClient();
        Request request = BaseRequest.newRequest().url(url)
                .addHeader("sign", sign).build();
/*                BaseRequest.builder.url("http://mmapi.yomei.tv/mm131/getHotPicList?lastIndex=-1")
                .addHeader("sign", "20B140BADA51CE0DECE7ACFC56A672C0")
                .build();*/
        final Call call = client.newCall(request);
        MainActivity.THREAD_POOL_EXECUTOR.execute(new Thread() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    ResponseBody body = response.body();
                    int code = response.code();
                    String string = body.string();
                    Log.d(TAG, "run: " + string);
                    SaveFile.saveFile(context, "showPhoto", "showPhoto", string);
                    Message message = handler.obtainMessage();
                    message.what = code;
                    Bundle bundle = new Bundle();
                    bundle.putString("showPhoto", string);
                    message.setData(bundle);
                    handler.sendMessage(message);
                    Log.d(TAG, "run: sendMessage");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
