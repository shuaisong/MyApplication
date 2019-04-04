package com.example.lenovo.myapplication.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.DetailImgUrl;
import com.example.lenovo.myapplication.utils.PreferenceManager;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/8/18.
 * auther:lenovo
 * Date：2018/8/18
 */

public class HVPadapter extends PagerAdapter {
    private List<DetailImgUrl.DataObjBean.PicUrlListBean> mList;
    private Context context;
    private List<PhotoView> viewList;
    private OnItemClickListener onItemClickListener;
    public HVPadapter(Context context, List<DetailImgUrl.DataObjBean.PicUrlListBean> mList) {
        this.context = context;
        this.mList = mList;
        this.viewList = new ArrayList<>();
        setViewList(mList);
    }

    @Override
    public int getCount() {//必须实现
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {//必须实现
        return view == object;
    }

    private void setViewList(final List<DetailImgUrl.DataObjBean.PicUrlListBean> mList) {
        this.mList = mList;
        for (DetailImgUrl.DataObjBean.PicUrlListBean bean :
                mList) {
            PhotoView mView = (PhotoView) LayoutInflater.from(context).inflate(R.layout.detail_pic_item, null);
            viewList.add(mView);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(viewList.size()-1,mList.get(viewList.size()-1));
                }
            });
        }
        for (int i = 0; i < 4; i++) {
            Picasso.get().load(PreferenceManager.getInstance().getPicUrlPrefix() + mList.get(i).getPic_url())
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .placeholder(R.mipmap.pic_classify_default)
                    .config(Bitmap.Config.RGB_565)
                    .tag(context)
                    .into(viewList.get(i));
        }
        notifyDataSetChanged();
    }

    public void loadPic(int position) {
        Picasso.get().load(PreferenceManager.getInstance().getPicUrlPrefix() + mList.get(position).getPic_url())
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .placeholder(R.mipmap.pic_classify_default)
                .config(Bitmap.Config.RGB_565)
                .tag(context)
                .into(viewList.get(position));
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }
    public void setOnitemClickListener(OnItemClickListener onitemClickListener){
        this.onItemClickListener = onitemClickListener;
    }
    interface OnItemClickListener{
        void onItemClick(int position, DetailImgUrl.DataObjBean.PicUrlListBean bean);
    }
}
