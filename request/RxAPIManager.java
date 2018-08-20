package com.example.lenovo.myapplication.request;

import android.annotation.TargetApi;
import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import rx.Subscription;

import static android.os.Build.VERSION_CODES.KITKAT;

/**
 * Created by lenovo on 2018/8/15.
 * auther:lenovo
 * Date：2018/8/15
 */

public class RxAPIManager implements APIActionManager {
    private static RxAPIManager sInstance = null;

    private ArrayMap<Object, List<Subscription>> maps;

    public static RxAPIManager getInstance() {

        if (sInstance == null) {
            synchronized (RxAPIManager.class) {
                if (sInstance == null) {
                    sInstance = new RxAPIManager();
                }
            }
        }
        return sInstance;
    }

    @TargetApi(KITKAT)
    private RxAPIManager() {
        this.maps = new ArrayMap<>();
    }

    /*
    作者：Tamic
    链接：https://www.jianshu.com/p/d62962243c33
    來源：简书
    简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。*/
    @TargetApi(KITKAT)
    @Override
    public <T> void add(T tag, Subscription subscription) {
        if (maps.containsKey(tag)) {
            maps.get(tag).add(subscription);
        } else {
            ArrayList<Subscription> mSubscriptions = new ArrayList<>();
            mSubscriptions.add(subscription);
            maps.put(tag, mSubscriptions);
        }
    }

    @Override
    public <T> void remove(T tag) {
        if (!maps.isEmpty()) maps.remove(tag);
    }

    @Override
    public <T> void cancel(T tag) {
        if (maps.isEmpty()) return;
        if (maps.get(tag) == null) return;
        for (Subscription sub :
                maps.get(tag)) {
            if (!sub.isUnsubscribed()) {
                sub.unsubscribe();
            }
        }
        maps.remove(tag);
    }

    @Override
    public void cancelAll() {
        if (!maps.isEmpty()) {
            Set<Object> mKeySet = maps.keySet();
            for (Object key :
                    mKeySet) {
                cancel(key);
            }
        }
    }
}
