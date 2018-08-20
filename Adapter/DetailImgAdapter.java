package com.example.lenovo.myapplication.Adapter;

import android.content.Context;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.DetailImgUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class DetailImgAdapter extends CommonBaseRecycleAdapter<DetailImgUrl.DataObjBean> {
    public DetailImgAdapter(Context context, List<DetailImgUrl.DataObjBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, DetailImgUrl.DataObjBean data, int position) {
        List<DetailImgUrl.DataObjBean.PicUrlListBean> mPicUrlList = data.getPicUrlList();
        ArrayList<String> urls = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();

        for (DetailImgUrl.DataObjBean.PicUrlListBean bean :
                mPicUrlList) {
            urls.add(bean.getPic_url());
            titles.add(bean.getPic_url());
        }
        holder.setData(mContext, R.id.vp, mPicUrlList);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.detail_img_item;
    }
}
