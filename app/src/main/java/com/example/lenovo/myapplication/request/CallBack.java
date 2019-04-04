package com.example.lenovo.myapplication.request;

import com.vise.xsnow.http.callback.ACallback;

/**
 * Created by lenovo on 2018/9/10.
 * auther:lenovo
 * Date：2018/9/10
 */
public class CallBack<T> extends ACallback<T> {
    @Override
    public void onSuccess(T data) {

    }

    @Override
    public void onFail(int errCode, String errMsg) {

    }
}
