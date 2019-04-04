package com.example.lenovo.myapplication.Contact;

import com.example.lenovo.myapplication.bean.LabelContent21;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public interface ImgVideoContact {
    interface View extends BaseContact.BaseView {
        void showImg(LabelContent21.DataObjBean dataObjBean);
        void showVieo(LabelContent21.DataObjBean dataObjBean);
    }

    interface Presenter<T> extends BaseContact.BasePresenter<T> {
        void LabelContent21( int contentType, int lastId, final String label_name,String sign);
    }
}
