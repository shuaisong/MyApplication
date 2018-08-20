package com.example.lenovo.myapplication.presenter;

import com.example.lenovo.myapplication.Contact.ImgFGContact;
import com.example.lenovo.myapplication.bean.HotPhoto;
import com.example.lenovo.myapplication.bean.NewPhoto;
import com.example.lenovo.myapplication.request.ComposObservable;
import com.example.lenovo.myapplication.request.RetrofitUtil;
import com.example.lenovo.myapplication.request.RxAPIManager;
import com.example.lenovo.myapplication.request.SimpleSubscriber;
import com.example.lenovo.myapplication.utils.ToastUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class ImgPresenter extends RxPresenter<ImgFGContact.View> implements ImgFGContact.Presenter<ImgFGContact.View> {
    @Inject
    ImgPresenter() {
    }

    @Override
    public void refreshHot(String sign, int lastIndex) {
        Subscription mSubscription = ComposObservable.observe(RetrofitUtil.createAPIService().getHotImg(sign, lastIndex))
                .subscribe(new SimpleSubscriber<HotPhoto>() {
                               @Override
                               public void onError(Throwable e) {
                                   super.onError(e);
                                   view.refreshHot(null);
                               }

                               @Override
                               public void call(HotPhoto hotPhoto) {
                                   if (hotPhoto.getCode() == 200) {
                                       view.refreshHot(hotPhoto);
                                   } else {
                                       ToastUtil.show(hotPhoto.getMsg());
                                       view.refreshHot(null);
                                   }

                               }
                           }
                );
        RxAPIManager.getInstance().add(this, mSubscription);
    }

    @Override
    public void refreshNew(String sign, int lastIndex) {
        Subscription mSubscription = ComposObservable.observe(RetrofitUtil.createAPIService()
                .getNewImg(sign, lastIndex)).subscribe(new SimpleSubscriber<NewPhoto>() {
            @Override
            public void call(NewPhoto newPhoto) {
                if (newPhoto.getCode() == 200) {
                    view.refreshNew(newPhoto);
                } else {
                    view.refreshNew(null);
                    ToastUtil.show(newPhoto.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.refreshNew(null);
            }
        });
        RxAPIManager.getInstance().add(this, mSubscription);
    }

    @Override
    public void detachView() {
        super.detachView();
        RxAPIManager.getInstance().cancel(this);
    }
}
