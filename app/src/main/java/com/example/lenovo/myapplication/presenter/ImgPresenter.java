package com.example.lenovo.myapplication.presenter;

import com.example.lenovo.myapplication.Contact.ImgFGContact;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.bean.HotPhoto;
import com.example.lenovo.myapplication.bean.NewPhoto;
import com.example.lenovo.myapplication.request.CallBack;
import com.example.lenovo.myapplication.utils.LogUtil;
import com.example.lenovo.myapplication.utils.ToastUtil;
import com.vise.xsnow.http.ViseHttp;

import javax.inject.Inject;

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
        ViseHttp.GET(Contant.getHotPicList).addHeader(Contant.token, Contant.tokenValue)
                .addHeader(Contant.sign, sign).tag(this)
                .addParam("lastIndex", String.valueOf(lastIndex))
                .request(new CallBack<HotPhoto>() {
            @Override
            public void onSuccess(HotPhoto hotPhoto) {
                if (hotPhoto.getCode() == 200) {
                    view.refreshHot(hotPhoto);
                } else
                    ToastUtil.show(hotPhoto.getMsg());
            }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        super.onFail(errCode, errMsg);
                        LogUtil.e(errCode+errMsg);
                    }
                });
        /*RetrofitUtil.getInstance().toSubscribe(RetrofitUtil.createAPIService().getHotImg(sign, lastIndex),
                ActivityLifeCycleEvent.DESTROY, ((ImgFG) view).lifecycleSubject,
                new JsonObserver<HotPhoto>() {
                    @Override
                    public void onResponse(HotPhoto hotPhoto) {
                        if (hotPhoto.getCode() == 200) {
                            view.refreshHot(hotPhoto);
                        } else
                            ToastUtil.show(hotPhoto.getMsg());
                    }
                });*/
      /*
        RxAPIManager.getInstance().add(this, mSubscription);*/
    }

    @Override
    public void refreshNew(String sign, int lastIndex) {
        ViseHttp.GET(Contant.getNewPicList).addHeader(Contant.token, Contant.tokenValue)
                .addHeader(Contant.sign, sign)
                .tag(this)
                .addParam("lastIndex", String.valueOf(lastIndex))
                .request(new CallBack<NewPhoto>() {
            @Override
            public void onSuccess(NewPhoto newPhoto) {
                if (newPhoto.getCode() == 200) {
                    view.refreshNew(newPhoto);
                } else
                    ToastUtil.show(newPhoto.getMsg());
            }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        super.onFail(errCode, errMsg);
                        LogUtil.d(errCode+errMsg);
                    }
                });
    }
}
