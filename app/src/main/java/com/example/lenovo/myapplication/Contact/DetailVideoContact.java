package com.example.lenovo.myapplication.Contact;

import com.example.lenovo.myapplication.bean.RecommandVideo;
import com.example.lenovo.myapplication.bean.VideoDetail; /**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public interface DetailVideoContact {
    interface View extends BaseContact.BaseView {
        void showVideo(VideoDetail.DataObjBean dataObj);

        void showRecommandVideo(RecommandVideo.DataObjBean dataObj);
    }

    interface Presenter<T> extends BaseContact.BasePresenter<T> {
        void getArticleVideoDetail(String avid, String sign);
        void getRecommandVideoList(String sign);
    }
}
