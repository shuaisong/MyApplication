package com.example.lenovo.myapplication.presenter;

import com.example.lenovo.myapplication.Contact.DetailImgContact;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.bean.DetailImgUrl;
import com.example.lenovo.myapplication.request.CallBack;
import com.example.lenovo.myapplication.utils.LogUtil;
import com.example.lenovo.myapplication.utils.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.mode.CacheMode;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class DetailImgPresenter extends RxPresenter<DetailImgContact.View> implements DetailImgContact.Presenter<DetailImgContact.View> {
    @Inject
    DetailImgPresenter() {
    }


    @Override
    public void getPicUrlList(int apid, String sign) {
        ViseHttp.GET (Contant.getArticlePicDetail).addHeader(Contant.token, Contant.tokenValue)
                .tag(this).cacheMode(CacheMode.FIRST_CACHE)
                .addHeader(Contant.sign, sign).addParam("apid", String.valueOf(apid))
                .request(new CallBack<DetailImgUrl>() {
            @Override
            public void onSuccess(DetailImgUrl detailImgUrl) {
                if (detailImgUrl.getCode() == 200) {
                    DetailImgUrl.DataObjBean mDataObj = detailImgUrl.getDataObj();
                   if (view!=null)
                    view.showImg(mDataObj);
                } else
                    ToastUtil.show(detailImgUrl.getMsg());
            }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        super.onFail(errCode, errMsg);
                        LogUtil.d(errCode+errMsg);
                    }
                });
    }


    @Override
    public void detachView() {
        ViseHttp .cancelTag(this);
        super.detachView();
    }

}
