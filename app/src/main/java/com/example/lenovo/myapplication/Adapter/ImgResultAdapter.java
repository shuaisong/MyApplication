package com.example.lenovo.myapplication.Adapter;

import android.content.Context;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.ArticleListBean;
import com.example.lenovo.myapplication.utils.PreferenceManager;

import java.util.List;

/**
 * Created by lenovo on 2018/9/2.
 * auther:lenovo
 * Date：2018/9/2
 */
public class ImgResultAdapter extends CommonBaseRecycleAdapter<ArticleListBean> {
    public ImgResultAdapter(Context context, List<ArticleListBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    Object object;

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    protected void convert(ViewHolder holder, ArticleListBean data, int position) {
        holder.setText(R.id.photo_list_item_title, data.getTitle());
        holder.setText(R.id.photo_list_item_num, data.getPic_num());
        holder.setImageUrl(R.id.photo_list_item_image,
                PreferenceManager.getInstance().getPicUrlPrefix() + data.getCover_url(),
                R.mipmap.pic_classify_default, object);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.img_list_item;
    }
}
