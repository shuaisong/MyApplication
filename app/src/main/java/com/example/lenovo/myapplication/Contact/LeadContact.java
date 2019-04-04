package com.example.lenovo.myapplication.Contact;

import com.example.lenovo.myapplication.bean.BaseUrl;
import com.example.lenovo.myapplication.bean.FeedBack;
import com.example.lenovo.myapplication.bean.RecommendLabel;
import com.example.lenovo.myapplication.bean.SearchType;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public interface LeadContact {
    interface View extends BaseContact.BaseView {
        void saveData(BaseUrl.DataObjBean.UrlInfoBean bean);
        void showFeedBack(FeedBack.DataObjBean feedBack);

        void saveSearchKey(SearchType.DataObjBean bean, String searchType);

        void saveRecommend(RecommendLabel.DataObjBean bean);
        void feedBack( );
    }

    interface Presenter<T> extends BaseContact.BasePresenter<T> {
        void initData();

        void checkVersion();

        void feedBack();

        void getSearchKey(String sign, String type);

        void getRecommend();
    }
}
