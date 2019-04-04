package com.example.lenovo.myapplication.bean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/8/26.
 * auther:lenovo
 * Date：2018/8/26
 */
public class ConvertCollection {
    public static MyCollection convert(@NotNull Collection collection) {
        MyCollection mMyCollection = new MyCollection();
        List<MyCollection.DataBean> mDataBeanList = new ArrayList<>();
        MyCollection.PicBean mPicBean;
        MyCollection.VideoBean mVideoBean;
        int picNum = collection.getDataObj().getPicCollectionNum();
        int videoNum = collection.getDataObj().getVideoCollectionNum();
        if (picNum != 0)
            for (int i = 0; i < picNum; i++) {
                List<Collection.DataObjBean.PicDetailBean> mPicDetail = collection.getDataObj().getPicDetail();
                List<Collection.DataObjBean.ArticlePicNumBean> mArticlePicNum = collection.getDataObj().getArticlePicNum();
                mPicBean = new MyCollection.PicBean();
                mPicBean.setPic_url(mPicDetail.get(i).getPic_url());
                mPicBean.setApid(String.valueOf(mArticlePicNum.get(i).getApid()));
                mPicBean.setWidth(mPicDetail.get(i).getWidth());
                mPicBean.setHeight(mPicDetail.get(i).getHeight());
                mPicBean.setType(MyCollection.PIC);
                mDataBeanList.add(mPicBean);
            }
        if (videoNum != 0)
            for (int i = 0; i < videoNum; i++) {
                List<Collection.DataObjBean.ArticleVideoDetailBean> mArticleVideoDetail = collection.getDataObj().getArticleVideoDetail();
                mVideoBean = new MyCollection.VideoBean();
                mVideoBean.setVideo_url(mArticleVideoDetail.get(i).getVideo_url());
                mVideoBean.setCover_url(mArticleVideoDetail.get(i).getCover_url());
                mVideoBean.setAvid(mArticleVideoDetail.get(i).getAvid());
                mVideoBean.setVideo_duration(mArticleVideoDetail.get(i).getVideo_duration());
                mVideoBean.setCover_width(mArticleVideoDetail.get(i).getCover_width());
                mVideoBean.setCover_height(mArticleVideoDetail.get(i).getCover_height());
                mVideoBean.setType(MyCollection.VEDIO);
                mDataBeanList.add(mVideoBean);
            }
        mMyCollection.setList(mDataBeanList);
        return mMyCollection;
    }
}
