package com.example.lenovo.myapplication.utils;

import okhttp3.Request;

/**
 * Created by lenovo on 2018/4/14.
 */

public class BaseRequest {
    public static Request.Builder newRequest() {
        Request.Builder builder = new Request.Builder().addHeader("appkey", "06E0C9C16A169AEC")
                .addHeader("api_version", "v20")
                .addHeader("applicationId", "1")
                .addHeader("applicationId", "1")
                .addHeader("channel", "GW_3")
                .addHeader("client_version", "1.6.3")
                .addHeader("refer", "http://mmapi.yomei.tv/")
                .addHeader("did", "352284048697324,-1,f19245f48e2ffff6")
                .addHeader("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjE1NzM3MDQ5OTIxLCJyYW5kb20iOjE1MjM1MzMyMjI3MzQsImxvZ2luX3R5cGUiOjF9.IAQ3_DO1v8nchLbXFyXTQ5sHtZ99qNNLnsyU9L1JIXM");;
        return builder;
    }

/*
    static Request.Builder builder = new Request.Builder()
            .addHeader("appkey", "06E0C9C16A169AEC")
            .addHeader("api_version", "v20")
            .addHeader("applicationId", "1")
            .addHeader("applicationId", "1")
            .addHeader("channel", "GW_3")
            .addHeader("client_version", "1.6.3")
            .addHeader("refer", "http://mmapi.yomei.tv/")
            .addHeader("did", "352284048697324,-1,f19245f48e2ffff6")
            .addHeader("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjE1NzM3MDQ5OTIxLCJyYW5kb20iOjE1MjM1MzMyMjI3MzQsImxvZ2luX3R5cGUiOjF9.IAQ3_DO1v8nchLbXFyXTQ5sHtZ99qNNLnsyU9L1JIXM");
*/

}
