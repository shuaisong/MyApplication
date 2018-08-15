package com.example.lenovo.myapplication.presenter;

import com.example.lenovo.myapplication.Contact.DetailImgContact;
import com.example.lenovo.myapplication.bean.ArticlePicDetail;
import com.example.lenovo.myapplication.bean.DetailImgUrl;
import com.example.lenovo.myapplication.request.RetrofitUtil;
import com.example.lenovo.myapplication.request.RequestApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class DetailImgPresenter extends RxPresenter<DetailImgContact.View> implements DetailImgContact.Presenter<DetailImgContact.View> {
    @Inject
    public DetailImgPresenter() {
    }

    private RequestApi api = RetrofitUtil.createAPIService();

    @Override
    public void getPicUrlList(int apid, String sign) {
        Call<DetailImgUrl> mCall = api.getDetailImgUrl(sign, apid);
        mCall.enqueue(new Callback<DetailImgUrl>() {
            @Override
            public void onResponse(Call<DetailImgUrl> call, Response<DetailImgUrl> response) {
                if (response.body() != null && response.body().getCode() == 200) {
                    DetailImgUrl.DataObjBean mDataObj = response.body().getDataObj();
                    view.showImg(mDataObj);
                }
            }

            @Override
            public void onFailure(Call<DetailImgUrl> call, Throwable t) {

            }
        });
    }

    @Override
    public void getArticlePicDetail(String sign, int apid, String articleType, String page) {
        Call<ArticlePicDetail> mCall = api.getArticleComment(sign, apid, articleType, page);
        mCall.enqueue(new Callback<ArticlePicDetail>() {
            @Override
            public void onResponse(Call<ArticlePicDetail> call, Response<ArticlePicDetail> response) {
                if (response.body() != null && response.body().getCode() == 200) {
                    ArticlePicDetail.DataObjBean mDataObj = response.body().getDataObj();
                    view.showArticle(mDataObj);
                }
            }

            @Override
            public void onFailure(Call<ArticlePicDetail> call, Throwable t) {

            }
        });
    }
}
