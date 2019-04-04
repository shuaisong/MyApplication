package com.example.lenovo.myapplication.presenter;

import com.example.lenovo.myapplication.Contact.CommentContact;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.bean.ArticlePicComment;
import com.example.lenovo.myapplication.request.CallBack;
import com.example.lenovo.myapplication.utils.LogUtil;
import com.example.lenovo.myapplication.utils.ToastUtil;
import com.vise.xsnow.http.ViseHttp;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class CommentPresenter extends RxPresenter<CommentContact.View> implements CommentContact.Presenter<CommentContact.View> {
    @Inject
    CommentPresenter() {
    }

    @Override
    public void getArticleCommentDetail(String sign, String aid, String articleType, String page) {
        ViseHttp.GET( Contant.getArticleComment).tag(this).addHeader(Contant.token, Contant.tokenValue)
                .addHeader(Contant.sign, sign)
                .addParam("aid", aid)
                .addParam("articleType", articleType)
                .addParam("page", page)
                .request(new CallBack<ArticlePicComment>() {
                    @Override
                    public void onSuccess(ArticlePicComment articlePicComment) {
                        if (articlePicComment.getCode() == 200) {
                            ArticlePicComment.DataObjBean mDataObj = articlePicComment.getDataObj();
                            view.showComment(mDataObj);
                        } else
                            ToastUtil.show(articlePicComment.getMsg());
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        super.onFail(errCode, errMsg);
                        LogUtil.d(errCode+errMsg);
                    }
                });
    }

    @Override
    public void detachView() {
        super.detachView();
        ViseHttp .cancelTag(this);
    }
}
