package com.example.lenovo.myapplication.Contact;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public interface MainContact {
    interface View extends BaseContact.BaseView {
    }

    interface Presenter<T> extends BaseContact.BasePresenter<T> {
        void downloadAPK(String url);
    }
}
