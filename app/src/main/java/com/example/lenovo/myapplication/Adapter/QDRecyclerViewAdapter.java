package com.example.lenovo.myapplication.Adapter;

/**
 * Created by lenovo on 2019/4/22.
 * auther:lenovo
 * Date：2019/4/22
 */

import android.content.Context;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.DetailImgUrl;
import com.example.lenovo.myapplication.utils.PreferenceManager;

import java.util.List;

public class QDRecyclerViewAdapter extends CommonBaseRecycleAdapter<DetailImgUrl.DataObjBean.PicUrlListBean> {


    public QDRecyclerViewAdapter(Context context, List<DetailImgUrl.DataObjBean.PicUrlListBean> datas, boolean isOpenLoadMore) {
        super(context, datas, false);
    }

    @Override
    protected void convert(ViewHolder holder, DetailImgUrl.DataObjBean.PicUrlListBean data, int position) {
        holder.setImageUrl(R.id.detail_img, PreferenceManager.getInstance().getPicUrlPrefix() + data.getPic_url(), R.mipmap.pic_classify_default, mContext);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.detail_pic_item;
    }


}
