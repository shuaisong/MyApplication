package com.example.lenovo.myapplication.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.SearchImgListBean;
import com.example.lenovo.myapplication.bean.SearchVideoListBean;
import com.example.lenovo.myapplication.utils.PreferenceManager;

import java.util.List;

/**
 * Created by lenovo on 2018/9/2.
 * auther:lenovo
 * Date：2018/9/2
 */
public class HotSearch2Adapter<T> extends CommonBaseRecycleAdapter<T> {
    private int type;

    public HotSearch2Adapter(Context context, int type, List<T> datas, boolean isOpenLoadMore, boolean haveheader) {
        super(context, datas, isOpenLoadMore, haveheader);
        this.type = type;
    }

    @Override
    protected void convert(ViewHolder holder, T data, int position) {
        if (type == 1) {
            TextView duration = holder.getView(R.id.duration);
            TextView count = holder.getView(R.id.play_count);
            SearchImgListBean mData = (SearchImgListBean) data;
            holder.setImageUrl(R.id.video_cover, PreferenceManager.getInstance().getPicThumbUrlprefix() + mData.getCover_url());
            holder.setText(R.id.title, mData.getTitle());
            final Drawable drawable_left= mContext.getResources().getDrawable(R.mipmap.icon_search_collect);
            drawable_left.setBounds(0,0,drawable_left.getMinimumWidth(),drawable_left.getMinimumHeight());
            count.setCompoundDrawables(drawable_left,null,null,null);
            count.setText( String.format(mContext.getString(R.string.num), mData.getClick_num()));
            duration.setVisibility(View.GONE);
        } else {
            SearchVideoListBean mData = (SearchVideoListBean) data;
            holder.setImageUrl(R.id.video_cover, PreferenceManager.getInstance().getVideoUrlPrefix() + mData.getCover_url());
            holder.setText(R.id.title, mData.getTitle());
            holder.setText(R.id.play_count, String.format(mContext.getString(R.string.play_n_count), mData.getClick_num()));
            holder.setText(R.id.duration, mData.getVideo_duration());
        }
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.recomandvideo_item;
    }
}
