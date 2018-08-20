package com.example.lenovo.myapplication.presenter;

import com.example.lenovo.myapplication.Contact.BaseContact;
import com.example.lenovo.myapplication.Contact.MainContact;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public class MainPresenter extends RxPresenter<MainContact.View> implements BaseContact.BasePresenter<MainContact.View> {
    @Inject
    MainPresenter() {
    }
}
