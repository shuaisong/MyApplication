package com.example.lenovo.myapplication.Contact;

import com.example.lenovo.myapplication.bean.DetailImgUrl;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public interface DetailImgContact {
    interface View extends BaseContact.BaseView {
        void showImg(DetailImgUrl.DataObjBean dataObj);
    }

    interface Presenter<T> extends BaseContact.BasePresenter<T> {
        void getPicUrlList(int apid, String sign);

    }
}
