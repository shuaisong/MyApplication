package com.example.lenovo.myapplication.request;

import rx.Subscription;

/**
 * Created by lenovo on 2018/8/15.
 * auther:lenovo
 * Date：2018/8/15
 */

public interface APIActionManager {

    <T> void add(T tag, Subscription subscription);

    <T> void remove(T tag);

    <T> void cancel(T tag);

    void cancelAll();
}
