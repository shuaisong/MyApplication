package com.example.lenovo.myapplication.presenter;

import com.example.lenovo.myapplication.Contact.DetailVideoContact;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.bean.RecommandVideo;
import com.example.lenovo.myapplication.bean.VideoDetail;
import com.example.lenovo.myapplication.request.CallBack;
import com.vise.xsnow.http.ViseHttp;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public class DetailVideoPresenter extends RxPresenter<DetailVideoContact.View> implements DetailVideoContact.Presenter<DetailVideoContact.View> {
    @Inject
    DetailVideoPresenter() {
    }

    @Override
    public void getArticleVideoDetail(String avid, String sign) {
        ViseHttp.GET( Contant.getArticleVideoDetail).addHeader(Contant.sign, sign)
                .addHeader(Contant.token, Contant.tokenValue).addParam("avid", avid)
                .tag(this)
                .request(new CallBack<VideoDetail>() {
                    @Override
                    public void onSuccess(VideoDetail videoDetail) {
                        if (videoDetail.getDataObj() != null&&view!=null)
                            view.showVideo(videoDetail.getDataObj());
                    }
                });
    }

    @Override
    public void getRecommandVideoList(String sign) {
        ViseHttp.GET (Contant.getRecommandVideoList).addHeader(Contant.sign, sign)
                .addHeader(Contant.token, Contant.tokenValue)
                .tag(this)
                .request(new CallBack<RecommandVideo>() {
                    @Override
                    public void onSuccess(RecommandVideo recommandVideo) {
                        if (recommandVideo.getDataObj() != null&&view!=null)
                            view.showRecommandVideo(recommandVideo.getDataObj());
                    }
                });
    }

    @Override
    public void detachView() {
        super.detachView();
        ViseHttp .cancelTag(this);
    }
}
