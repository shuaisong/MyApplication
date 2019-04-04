package com.example.lenovo.myapplication.Adapter;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.NewVideo;
import com.example.lenovo.myapplication.utils.PreferenceManager;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;


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

    public void setImageUrl( int viewId, String imgUrl, int placeHolderRes,Object tag) {
        ImageView view = getView(viewId);
        setImageUrl(view,imgUrl,placeHolderRes,tag);
    }
    public void setImageUrl( ImageView imageView, String imgUrl, int placeHolderRes,Object tag) {
        Picasso.get().load(imgUrl)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .placeholder(placeHolderRes)
                .config(Bitmap.Config.RGB_565)
                .tag(tag)
                .into(imageView);
    }

    public void setImageUrl(int viewId, String imgUrl ) {
        ImageView view = getView(viewId);
        Picasso.get().load(imgUrl)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .config(Bitmap.Config.RGB_565)
                .into(view);
    }

    public void setImageUrl(int viewId, int imgUrl) {
        ImageView view = getView(viewId);
        Picasso.get().load(imgUrl)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .config(Bitmap.Config.RGB_565)
                .into(view);
    }

    public void setVideoInfo(int video_player, NewVideo.DataObjBean.NewVideoListBean data) {
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        JZVideoPlayerStandard player=getView(video_player);
        player.setUp(data.getVideo_url(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, data.getTitle());
        String coverUri = PreferenceManager.getInstance().getVideoUrlPrefix() + data.getCover_url();
        setImageUrl(player.thumbImageView,coverUri,R.mipmap.pic_classify_default,player.getContext());
    }
}
