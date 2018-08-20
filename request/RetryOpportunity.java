package com.example.lenovo.myapplication.request;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/8/15.
 * auther:lenovo
 * Date：2018/8/15
 */

public class RetryOpportunity implements Func1<Observable<? extends Throwable>, Observable<Long>> {
    private static final int INITIAL = 1;
    private int maxConnectCount = 1;

    public RetryOpportunity(int retryCount) {
        this.maxConnectCount += retryCount;
    }

    @Override
    public Observable<Long> call(Observable<? extends Throwable> errorObservable) {
        return errorObservable.zipWith(Observable.range(INITIAL, maxConnectCount),
                new Func2<Throwable, Integer, ThrowableWrapper>() {

                    @Override
                    public ThrowableWrapper call(Throwable throwable, Integer integer) {
                        if (throwable instanceof IOException)
                            return new ThrowableWrapper(throwable, integer);
                        return new ThrowableWrapper(throwable, maxConnectCount);
                    }
                }).concatMap(new Func1<ThrowableWrapper, Observable<? extends Long>>() {
            @Override
            public Observable<? extends Long> call(ThrowableWrapper throwableWrapper) {
                final int retryCount = throwableWrapper.getRetryCount();
                if (retryCount == maxConnectCount) {
                    return Observable.error(throwableWrapper.getSourceThrowable());
                }

                return Observable.timer((long) Math.pow(2, retryCount), TimeUnit.SECONDS, Schedulers.immediate());
            }
        });
    }

    private static final class ThrowableWrapper {

        private Throwable sourceThrowable;
        private Integer retryCount;

        ThrowableWrapper(Throwable sourceThrowable, Integer retryCount) {
            this.sourceThrowable = sourceThrowable;
            this.retryCount = retryCount;
        }

        Throwable getSourceThrowable() {
            return sourceThrowable;
        }

        Integer getRetryCount() {
            return retryCount;
        }
    }

/*    作者：小鄧子
    链接：https://www.jianshu.com/p/fca90d0da2b5
    來源：简书*/
}
