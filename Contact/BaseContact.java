package com.example.lenovo.myapplication.Contact;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public interface BaseContact {
    interface BaseView<T> {
        void start();

        void compete();
    }

    interface BasePresenter<T> {

        void attachView(T view);

        void detachView();
    }
}
