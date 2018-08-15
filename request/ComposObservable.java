package com.example.lenovo.myapplication.request;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/8/15.
 * auther:lenovo
 * Date：2018/8/15
 */

public class ComposObservable {
    public static <T> Observable<T> observe(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryOpportunity(3))//重试次数
                .cache();

    }
}
