package com.example.lenovo.myapplication.Adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.DetailImgUrl;
import com.example.lenovo.myapplication.utils.PreferenceManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Time: 2016/8/29 09:49
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;

    /**
     * 私有构造方法
     *
     * @param itemView
     */
    private ViewHolder(View itemView) {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    /*public static ViewHolder create(Context context, int layoutId, ViewGroup parent) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mContext=context;
        return new ViewHolder(itemView);
    }
*/
    public static ViewHolder create(View itemView) {
        return new ViewHolder(itemView);
    }

    /**
     * 通过id获得控件
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public View getSwipeView() {
        ViewGroup itemLayout = ((ViewGroup) mConvertView);
        if (itemLayout.getChildCount() == 2) {
            return itemLayout.getChildAt(1);
        }
        return null;
    }

    public void setData(Context context, int viewId, List<DetailImgUrl.DataObjBean.PicUrlListBean> beans) {
        ViewPager mViewPager = getView(viewId);
        List<View> mViewList = new ArrayList<>();
        String mPicUrlPrefix = PreferenceManager.getInstance().getPicUrlPrefix();
        for (DetailImgUrl.DataObjBean.PicUrlListBean mBean :
                beans) {
            View mView = LayoutInflater.from(context).inflate(R.layout.detail_pic_item, null);
            ImageView mImageView = mView.findViewById(R.id.detail_img);
            Picasso.get().load(mPicUrlPrefix + mBean.getPic_url()).placeholder(R.mipmap.icon_network_error).into(mImageView);
            mViewList.add(mView);
        }
        VPadapter mVPadapter = new VPadapter(mViewList);
        mViewPager.setAdapter(mVPadapter);

    }

    public void setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
    }

    public void setText(int viewId, int textId) {
        TextView textView = getView(viewId);
        textView.setText(textId);
    }

    public void setTextColor(int viewId, int colorId) {
        TextView textView = getView(viewId);
        textView.setTextColor(colorId);
    }

    public void setOnClickListener(int viewId, View.OnClickListener clickListener) {
        View view = getView(viewId);
        view.setOnClickListener(clickListener);
    }

    public void setBgRes(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
    }

    public void setBgColor(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(colorId);
    }

    public void setImageUrl(int viewId, String imgUrl, int placeHolderRes) {
        ImageView view = getView(viewId);
        Picasso.get().load(imgUrl).placeholder(placeHolderRes).into(view);
    }

    public void setImageUrl(int viewId, String imgUrl) {
        setImageUrl(viewId, imgUrl, R.mipmap.icon_network_error);
    }

    public void setImageUrl(int viewId, int imgUrl) {
        ImageView view = getView(viewId);
        Picasso.get().load(imgUrl).into(view);
    }

}
