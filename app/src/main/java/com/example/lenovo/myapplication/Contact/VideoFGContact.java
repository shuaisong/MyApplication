package com.example.lenovo.myapplication.Contact;

import com.example.lenovo.myapplication.bean.NewVideo;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public interface VideoFGContact {
    interface View extends BaseContact.BaseView {
        void showVideos(NewVideo.DataObjBean dataObj);

        void showBanner(Map<String, List<String>> map);
    }

    interface Presenter<T> extends BaseContact.BasePresenter<T> {

        void getVideos(String sign, String lastIndex);

        void gerAD();
    }
}
