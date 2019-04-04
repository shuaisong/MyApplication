package com.example.lenovo.myapplication.Adapter;

import android.content.Context;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.VideoListBean;
import com.example.lenovo.myapplication.utils.PreferenceManager;
import com.example.lenovo.myapplication.view.activity.Img_Video_Result;

import java.util.List;

/**
 * Created by lenovo on 2018/9/2.
 * auther:lenovo
 * Date：2018/9/2
 */
public class VideoResultAdapter extends CommonBaseRecycleAdapter<VideoListBean> {
    private Img_Video_Result object;

    public VideoResultAdapter(Context context, List<VideoListBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, VideoListBean data, int position) {
        holder.setImageUrl(R.id.video_cover,
                PreferenceManager.getInstance().getVideoUrlPrefix() + data.getCover_url(),
                R.mipmap.icon_search_label_normal, object);
        holder.setText(R.id.title, data.getTitle());
        holder.setText(R.id.play_count, String.format(mContext.getString(R.string.play_n_count), data.getClick_num()));
        holder.setText(R.id.duration, data.getVideo_duration());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.recomandvideo_item;
    }

    public void setObject(Img_Video_Result object) {
        this.object = object;
    }
}
