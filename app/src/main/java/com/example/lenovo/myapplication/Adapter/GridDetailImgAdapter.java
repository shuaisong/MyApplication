package com.example.lenovo.myapplication.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.DetailImgUrl;
import com.example.lenovo.myapplication.utils.PreferenceManager;
import com.example.lenovo.myapplication.view.StrokeImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo on 2018/8/31.
 * auther:lenovo
 * Date：2018/8/31
 */
public class GridDetailImgAdapter extends CommonBaseRecycleAdapter<DetailImgUrl.DataObjBean.PicUrlListBean> {
    private int index;
    public GridDetailImgAdapter(Context context, List<DetailImgUrl.DataObjBean.PicUrlListBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }
    public GridDetailImgAdapter(Context context, List<DetailImgUrl.DataObjBean.PicUrlListBean> datas,int position) {
        super(context, datas, false);
        index =position;
    }
    private Object object;

    public void setTag(Object o) {
        object = o;
    }

    @Override
    protected void convert(ViewHolder holder, DetailImgUrl.DataObjBean.PicUrlListBean data, int position) {
        final StrokeImageView imageView = holder.getView(R.id.img);
        final ImageView choice = holder.getView(R.id.choice);
        ViewGroup.LayoutParams mLayoutParams = imageView.getLayoutParams();
//        mLayoutParams.width = data.getWidth();
        mLayoutParams.height = data.getHeight();
        imageView.setLayoutParams(mLayoutParams);
        if (position==index-1){
            imageView.setSelect(true);
           choice.setVisibility(View.VISIBLE);
             /*imageView.setImageResource(R.drawable.img_select_bg);*/
        }else {
            imageView.setSelect(false);
            choice.setVisibility(View.GONE);
            imageView.setImageResource(android.R.color.transparent);
        }
        Picasso.get().load(PreferenceManager.getInstance().getPicUrlPrefix() + data.getPic_url())
                .placeholder(R.mipmap.pic_classify_default).into(imageView/*new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imageView.setBackground(new BitmapDrawable(mContext.getResources(),bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                imageView.setBackground(placeHolderDrawable);
            }
        }*/);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.grid_detail_item;
    }
}
