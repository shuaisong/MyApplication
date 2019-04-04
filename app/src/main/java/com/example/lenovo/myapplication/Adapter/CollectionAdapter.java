package com.example.lenovo.myapplication.Adapter;

import android.content.Context;
import android.view.View;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.MyCollection;
import com.example.lenovo.myapplication.utils.PreferenceManager;

import java.util.List;

/**
 * Created by lenovo on 2018/8/26.
 * auther:lenovo
 * Date：2018/8/26
 */
public class CollectionAdapter extends CommonBaseRecycleAdapter<MyCollection.DataBean> {
    private final String baseImgUrl = PreferenceManager.getInstance().getPicThumbUrlprefix();
    private final String baseVideoUrl = PreferenceManager.getInstance().getVideoUrlPrefix();
    private Object tag;

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public CollectionAdapter(Context context, List<MyCollection.DataBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }
    @Override
    protected void convert(ViewHolder holder, MyCollection.DataBean mBean, int position) {
        int mType = mBean.getType();

        switch (mType) {
            case MyCollection.PIC:
                holder.setImageUrl(R.id.cover, baseImgUrl + ((MyCollection.PicBean) mBean).getPic_url(), R.mipmap.pic_classify_default, tag);
                break;
            case MyCollection.VEDIO:
                holder.setImageUrl(R.id.cover, baseVideoUrl + ((MyCollection.VideoBean) mBean).getCover_url(), R.mipmap.pic_classify_default, tag);
                holder.setText(R.id.tv_duration, ((MyCollection.VideoBean) mBean).getVideo_duration());
                holder.getView(R.id.tv_duration).setVisibility(View.VISIBLE);
                holder.getView(R.id.type).setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.cellect_item;
    }
}
