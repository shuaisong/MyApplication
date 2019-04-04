package com.example.lenovo.myapplication.Contact;

import com.example.lenovo.myapplication.bean.Collection;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public interface CollectionContact {
    interface View extends BaseContact.BaseView {
        void showCollection(Collection collection);
    }

    interface Presenter<T> extends BaseContact.BasePresenter<T> {
        void getCollectionList(String lastIndex, String collectionType);
    }
}
