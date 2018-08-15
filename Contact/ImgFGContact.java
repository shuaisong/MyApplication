package com.example.lenovo.myapplication.Contact;

import com.example.lenovo.myapplication.bean.HotPhoto;
import com.example.lenovo.myapplication.bean.NewPhoto;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public interface ImgFGContact {
    interface View extends BaseContact.BaseView {
        void refreshHot(HotPhoto hotPhoto);

        void refreshNew(NewPhoto newPhoto);
    }

    interface Presenter<T> extends BaseContact.BasePresenter<T> {

        void refreshNew(String sign, int lastIndex);

        void refreshHot(String sign, int lastIndex);
    }
}
