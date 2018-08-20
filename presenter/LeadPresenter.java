package com.example.lenovo.myapplication.presenter;

import com.example.lenovo.myapplication.Contact.LeadContact;
import com.example.lenovo.myapplication.bean.BaseUrl;
import com.example.lenovo.myapplication.bean.FeedBack;
import com.example.lenovo.myapplication.bean.RecommendLabel;
import com.example.lenovo.myapplication.bean.SearchType;
import com.example.lenovo.myapplication.bean.VersionCode;
import com.example.lenovo.myapplication.request.ComposObservable;
import com.example.lenovo.myapplication.request.RetrofitUtil;
import com.example.lenovo.myapplication.request.SimpleSubscriber;
import com.example.lenovo.myapplication.utils.ToastUtil;

import javax.inject.Inject;

import rx.Observable;

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
        ComposObservable.observe(RetrofitUtil.createAPIService().initData()).subscribe(new SimpleSubscriber<BaseUrl>() {
            @Override
            public void call(BaseUrl baseUrl) {
                if (baseUrl.getCode() == 200) {
                    view.saveData(baseUrl.getDataObj().getUrlInfo());
                } else
                    ToastUtil.show(baseUrl.getMsg());
            }
        });
    }

    @Override
    public void checkVersion() {
        ComposObservable.observe(RetrofitUtil.createAPIService().checkVersion("212")).subscribe(new SimpleSubscriber<VersionCode>() {
            @Override
            public void call(VersionCode versionCode) {
                if (versionCode.getCode() == 200) {
                    view.showVersion(versionCode.getDataObj());
                } else
                    ToastUtil.show(versionCode.getMsg());
            }
        });
    }

    @Override
    public void feedBack() {
        ComposObservable.observe(RetrofitUtil.createAPIService().feedBack()).subscribe(new SimpleSubscriber<FeedBack>() {
            @Override
            public void call(FeedBack feedBack) {
                if (feedBack.getCode() == 200) {
                    view.showFeedBack(feedBack.getDataObj());
                } else
                    ToastUtil.show(feedBack.getMsg());
            }
        });
    }

    @Override
    public void getSearchKey() {
        Observable<SearchType> mObserveType1 = ComposObservable.observe(RetrofitUtil.createAPIService().getSearchKeywordsList1("1"));
        Observable<SearchType> mObserveType2 = ComposObservable.observe(RetrofitUtil.createAPIService().getSearchKeywordsList2("2"));
        mObserveType1.subscribe(new SimpleSubscriber<SearchType>() {
            @Override
            public void call(SearchType searchType) {
                if (searchType.getCode() == 200) {
                    view.saveSearchKey(searchType.getDataObj(), "1");
                } else
                    ToastUtil.show(searchType.getMsg());
            }
        });
        mObserveType2.subscribe(new SimpleSubscriber<SearchType>() {
            @Override
            public void call(SearchType searchType) {
                if (searchType.getCode() == 200) {
                    view.saveSearchKey(searchType.getDataObj(), "2");
                } else
                    ToastUtil.show(searchType.getMsg());
            }
        });
    }

    @Override
    public void getRecommend() {
        ComposObservable.observe(RetrofitUtil.createAPIService().getRecommendLabelList21()).subscribe(new SimpleSubscriber<RecommendLabel>() {
            @Override
            public void call(RecommendLabel recommendLabel) {
                if (recommendLabel.getCode() == 200) {
                    view.saveRecommend(recommendLabel.getDataObj());
                } else
                    ToastUtil.show(recommendLabel.getMsg());
            }
        });
    }
}
