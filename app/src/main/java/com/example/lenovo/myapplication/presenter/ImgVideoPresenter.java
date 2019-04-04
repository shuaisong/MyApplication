package com.example.lenovo.myapplication.presenter;

import com.example.lenovo.myapplication.Contact.ImgVideoContact;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.bean.LabelContent21;
import com.example.lenovo.myapplication.request.CallBack;
import com.vise.xsnow.http.ViseHttp;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public class ImgVideoPresenter extends RxPresenter<ImgVideoContact.View> implements ImgVideoContact.Presenter<ImgVideoContact.View> {
    @Inject
    ImgVideoPresenter() {
    }

    @Override
    public void LabelContent21(final int contentType, int lastId, String label_name,String sign) {
        ViseHttp.GET (Contant.labelContent21).addHeader(Contant.sign, sign)
                .addHeader(Contant.token,Contant.tokenValue).addParam("contentType",String.valueOf(contentType))
                .addParam("lastId",String.valueOf(lastId)).addParam("pageSize",String.valueOf(12))
                .addParam("tag",label_name).request(new CallBack<LabelContent21>() {
            @Override
            public void onSuccess(LabelContent21 labelContent21) {
                if (contentType==1){
                    if (labelContent21.getCode()!=200){
                        view.showImg(null);
                    }else
                    view.showImg(labelContent21.getDataObj());
                }else {
                    if (labelContent21.getCode()!=200){
                        view.showVieo(null);
                    }else
                    view.showVieo(labelContent21.getDataObj());
                }
            }
        });
    }

    @Override
    public void detachView() {
        ViseHttp .cancelTag(this);
        super.detachView();
    }
}
