package com.example.lenovo.myapplication.Adapter;

import android.content.Context;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.ArticlePicComment;

import java.util.Calendar;
import java.util.List;

/**
 * Created by lenovo on 2018/8/23.
 * auther:lenovo
 * Date：2018/8/23
 */
public class CommentAdapter extends CommonBaseRecycleAdapter<ArticlePicComment.DataObjBean.ArticleCommentListBean> {
    public CommentAdapter(Context context, List<ArticlePicComment.DataObjBean.ArticleCommentListBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, ArticlePicComment.DataObjBean.ArticleCommentListBean data, int position) {
        holder.setText(R.id.nickName, data.getUser_nickname());
        holder.setText(R.id.content, data.getContent());
        long mGmt_create = data.getGmt_create();
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(mGmt_create);
        int month = mCalendar.get(Calendar.MONTH);
        holder.setText(R.id.comment_date, String.format(mContext.getString(R.string.comment_time), month));
        holder.setImageUrl(R.id.img, data.getUser_avatar_url());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.comment_item;
    }
}
