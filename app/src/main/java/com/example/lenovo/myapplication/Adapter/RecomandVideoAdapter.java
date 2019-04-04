package com.example.lenovo.myapplication.Adapter;

import android.content.Context;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.RecommandVideo;
import com.example.lenovo.myapplication.utils.PreferenceManager;

import java.util.List;

/**
 * Created by lenovo on 2018/9/1.
 * auther:lenovo
 * Date：2018/9/1
 */
public class RecomandVideoAdapter extends CommonBaseRecycleAdapter<RecommandVideo.DataObjBean.HotPicListBean> {
    public RecomandVideoAdapter(Context context, List<RecommandVideo.DataObjBean.HotPicListBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, RecommandVideo.DataObjBean.HotPicListBean data, int position) {
        holder.setImageUrl(R.id.video_cover, PreferenceManager.getInstance().getVideoUrlPrefix()+data.getCover_url());
        holder.setText(R.id.title,data.getTitle());
        holder.setText(R.id.play_count, String.format(mContext.getString(R.string.play_n_count), data.getClick_num()));
        holder.setText(R.id.duration,data.getVideo_duration());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.recomandvideo_item;
    }
}
