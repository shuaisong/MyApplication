package com.example.lenovo.myapplication.presenter;

import com.example.lenovo.myapplication.Contact.CollectionContact;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.bean.Collection;
import com.example.lenovo.myapplication.request.CallBack;
import com.example.lenovo.myapplication.utils.LogUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.mode.CacheMode;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public class CollectionPresenter extends RxPresenter<CollectionContact.View> implements CollectionContact.Presenter<CollectionContact.View> {
    @Inject
    CollectionPresenter() {
    }

    @Override
    public void getCollectionList(String lastIndex, String collectionType) {
        ViseHttp.GET (Contant.getCollectionList).tag(this).cacheMode(CacheMode.FIRST_REMOTE)
                .addHeader(Contant.sign,Contant.CollectionListSign)
                .addHeader(Contant.token,Contant.tokenValue)
                .addParam("lastIndex",lastIndex)
                .addParam("collectionType",collectionType)
                .request(new CallBack<Collection>() {
                    @Override
                    public void onSuccess(Collection collection) {
                        if (collection != null) {
                            view.showCollection(collection);
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        super.onFail(errCode, errMsg);
                        LogUtil.d(errCode + errMsg);
                    }
                });
    }

    @Override
    public void detachView() {
        ViseHttp.cancelTag(this);
        super.detachView();
    }
}
