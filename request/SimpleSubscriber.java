package com.example.lenovo.myapplication.request;

import com.example.lenovo.myapplication.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;
import rx.Subscriber;

/**
 * Created by lenovo on 2018/8/14.
 * auther:lenovo
 * Date：2018/8/14
 */

public abstract class SimpleSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof APIException) {
            APIException exception = (APIException) e;
            ToastUtil.show(exception.getMessage());
        } else if (e instanceof UnknownHostException) {
            ToastUtil.show("请打开网络");
        } else if (e instanceof SocketTimeoutException) {
            ToastUtil.show("请求超时");
        } else if (e instanceof ConnectException) {
            ToastUtil.show("连接失败");
        } else if (e instanceof HttpException) {
            ToastUtil.show("请求超时");
        } else {
            ToastUtil.show("请求失败");
        }
        e.printStackTrace();
    }


    @Override
    public void onNext(T t) {
        if (t != null) {//这里最好判断一下是否为null.
            call(t);
        } else {
            ToastUtil.show("连接失败");
        }
    }

    public abstract void call(T t);
}
