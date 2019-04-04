package com.example.lenovo.myapplication.presenter;

import com.example.lenovo.myapplication.Contact.LeadContact;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.bean.BaseUrl;
import com.example.lenovo.myapplication.bean.FeedBack;
import com.example.lenovo.myapplication.bean.RecommendLabel;
import com.example.lenovo.myapplication.bean.SearchType;
import com.example.lenovo.myapplication.bean.VersionCode;
import com.example.lenovo.myapplication.bean.msg.FindVersion;
import com.example.lenovo.myapplication.request.CallBack;
import com.example.lenovo.myapplication.utils.LogUtil;
import com.example.lenovo.myapplication.utils.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.mode.CacheMode;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public class LeadPresenter extends RxPresenter<LeadContact.View> implements LeadContact.Presenter<LeadContact.View> {
    @Inject
    LeadPresenter() {
    }


    @Override
    public void initData() {
        ViseHttp.GET(Contant.initData)
                .addHeader(Contant.sign, Contant.initDataSign)
                .addHeader(Contant.token, Contant.tokenValue)
                .tag(this).cacheMode(CacheMode.FIRST_CACHE)
                .request(new CallBack<BaseUrl>() {
                    @Override
                    public void onSuccess(BaseUrl baseUrl) {
                        view.feedBack();
                        if (baseUrl.getCode() == 200&&baseUrl.getDataObj()!=null) {
                            view.saveData(baseUrl.getDataObj().getUrlInfo());

                        } else
                            ToastUtil.show(baseUrl.getMsg());
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        super.onFail(errCode,errMsg);
                        view.feedBack();
                        LogUtil.d(errCode+errMsg);
                    }
                });
       /* RetrofitUtil.getInstance().toSubscribe(RetrofitUtil.createAPIService().initData(),
                ActivityLifeCycleEvent.DESTROY, ((LeadActivity) view).lifecycleSubject,
                new JsonObserver<BaseUrl>() {
                    @Override
                    public void onResponse(BaseUrl baseUrl) {
                        if (baseUrl.getCode() == 200) {
                            view.saveData(baseUrl.getDataObj().getUrlInfo());
                        } else
                            ToastUtil.show(baseUrl.getMsg());
                    }
                });*/
    }

    @Override
    public void checkVersion() {
        ViseHttp.GET(Contant.checkVersion)
                .addHeader(Contant.sign, Contant.versionSign)
                .tag(this).cacheMode(CacheMode.CACHE_AND_REMOTE)
                .addHeader(Contant.token, "")
                .addParam("versionCode", "212")
                .request(new CallBack<VersionCode>() {
            @Override
            public void onSuccess(VersionCode versionCode) {
                view.feedBack();
                if (versionCode.getCode() == 200&&versionCode.getDataObj()!=null) {
                    FindVersion mFindVersion = new FindVersion(versionCode.getDataObj());
                    EventBus.getDefault().postSticky(mFindVersion);
                } else
                    ToastUtil.show(versionCode.getMsg());
            }

            @Override
            public void onFail(int errCode, String errMsg) {
                view.feedBack();
                LogUtil.d(errCode+errMsg);
                super.onFail(errCode,errMsg);
            }
        });
    }

    @Override
    public void feedBack() {
        ViseHttp.GET(Contant.feedbackStatus)
                .addHeader(Contant.token, Contant.tokenValue)
                .tag(this).cacheMode(CacheMode.FIRST_CACHE)
                .addHeader(Contant.sign, Contant.feedbacksign)
                .request(new CallBack<FeedBack>() {
            @Override
            public void onSuccess(FeedBack feedBack) {
                view.feedBack();
                if (feedBack.getCode() == 200&&feedBack.getDataObj()!=null) {
                    view.showFeedBack(feedBack.getDataObj());
                } else
                    ToastUtil.show(feedBack.getMsg());
            }

            @Override
            public void onFail(int errCode, String errMsg) {
                super.onFail(errCode,errMsg);
                LogUtil.d(errCode+errMsg);
                view.feedBack();
            }
        });
    }

    @Override
    public void getSearchKey(String sign, final String type) {//type 1、2
        ViseHttp.GET(Contant.getSearchKeywordsList21).addHeader(Contant.token, Contant.tokenValue)
                .tag(this).cacheMode(CacheMode.FIRST_CACHE)
                .addHeader(Contant.sign, sign)
                .addParam("searchType", type)
                .request(new CallBack<SearchType>() {
            @Override
            public void onSuccess(SearchType searchType) {
                view.feedBack();
                if (searchType.getCode() == 200&&searchType.getDataObj()!=null) {
                    view.saveSearchKey(searchType.getDataObj(), type);
                } else
                    ToastUtil.show(searchType.getMsg());
            }

            @Override
            public void onFail(int errCode, String errMsg) {
                super.onFail(errCode,errMsg);
                LogUtil.d(errCode+errMsg);
                view.feedBack();
            }
        });
    }

    @Override
    public void getRecommend() {
        ViseHttp.GET(Contant.getRecommendLabelList21).addHeader(Contant.token, Contant.tokenValue)
                .tag(this).cacheMode(CacheMode.FIRST_CACHE)
                .addHeader(Contant.sign, Contant.recommendsign)
                .request(new CallBack<RecommendLabel>() {
            @Override
            public void onSuccess(RecommendLabel recommendLabel) {
                view.feedBack();
                if (recommendLabel.getCode() == 200&&recommendLabel.getDataObj()!=null) {
                    view.saveRecommend(recommendLabel.getDataObj());
                } else
                    ToastUtil.show(recommendLabel.getMsg());
            }

            @Override
            public void onFail(int errCode, String errMsg) {
                super.onFail(errCode,errMsg);
                LogUtil.d(errCode+errMsg);
                view.feedBack();
            }
        });
    }
}
