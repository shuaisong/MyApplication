package com.example.lenovo.myapplication.Contact;

import com.example.lenovo.myapplication.bean.ArticlePicComment;
import com.example.lenovo.myapplication.bean.DetailImgUrl;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public interface CommentContact {
    interface View extends BaseContact.BaseView {
        void showComment(ArticlePicComment.DataObjBean dataObj);
    }

    interface Presenter<T> extends BaseContact.BasePresenter<T> {
        void getArticleCommentDetail(String sign, String apid, String articleType, String page);
    }
}
