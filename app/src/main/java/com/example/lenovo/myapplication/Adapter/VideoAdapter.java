package com.example.lenovo.myapplication.Adapter;

import android.content.Context;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.NewVideo;
import com.example.lenovo.myapplication.utils.MyImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.List;

/**
 * Created by lenovo on 2018/8/22.
 * auther:lenovo
 * Date：2018/8/22
 */
public class VideoAdapter extends CommonBaseRecycleAdapter<NewVideo.DataObjBean.NewVideoListBean> {
    public VideoAdapter(Context context, List<NewVideo.DataObjBean.NewVideoListBean> datas, boolean isOpenLoadMore,boolean haveHeader) {
        super(context, datas, isOpenLoadMore,haveHeader);
    }

    @Override
    protected void convert(ViewHolder holder, NewVideo.DataObjBean.NewVideoListBean data, int position) {
        holder.setText(R.id.praise_num, data.getThumb_num());
        holder.setText(R.id.collect_num, data.getCollect_num());
        if (!"0".equals(data.getComment_num()))
        holder.setText(R.id.comment, data.getCollect_num());
        holder.setVideoInfo(R.id.video_player,data);
    }
    public void setBanner(Banner banner, List<String> title, List<String> url){
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyImageLoader());

        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);

        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
        //设置轮播图的标题集合
        banner.setBannerTitles(title);
        //设置图片网址或地址的集合
        banner.setImages(url).start();
        //必须最后调用的方法，启动轮播图。
    }
    @Override
    protected int getItemLayoutId() {
        return R.layout.video_item;
    }

}
