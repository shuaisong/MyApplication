package com.example.lenovo.myapplication.Adapter;

import android.content.Context;
import android.widget.ImageView;

import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.PicListBean;

import java.util.List;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public class ImgAdapter extends CommonBaseRecycleAdapter<PicListBean> {

    private int itemLayoutId;

    public ImgAdapter(Context context, List<PicListBean> datas, boolean isOpenLoadMore, int itemLayoutId) {
        super(context, datas, isOpenLoadMore);
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    protected void convert(ViewHolder holder, PicListBean data, int position) {
        holder.setText(R.id.photo_list_item_num, data.getPic_num());
        holder.setText(R.id.photo_list_item_title, data.getTitle());
        ImageView mImageView = holder.getView(R.id.photo_list_item_image);
        mImageView.setMinimumHeight(Integer.valueOf(data.getCover_height()));
        mImageView.setMinimumWidth(Integer.valueOf(data.getCover_width()));
        holder.setImageUrl(R.id.photo_list_item_image, Contant.baseUrl_img + data.getCover_url(), R.mipmap.pic_classify_default);
    }

    @Override
    protected int getItemLayoutId() {
        return row == 1 ? R.layout.photo_list_item1 : R.layout.photo_list_item2;
    }

    private int row = 1;

    public void setItemLayoutId(int row) {
        this.row = row;
    }
}
