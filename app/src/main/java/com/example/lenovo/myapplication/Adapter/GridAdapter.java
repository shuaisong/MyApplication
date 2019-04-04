package com.example.lenovo.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.RecommendLabel;
import com.example.lenovo.myapplication.utils.PreferenceManager;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo on 2018/9/1.
 * auther:lenovo
 * Date：2018/9/1
 */
public class GridAdapter<T extends RecommendLabel.DataObjBean.LabelListBean> extends BaseAdapter {
    private List<T> labelListBeans;
    private Context mContext;

    public GridAdapter(Context context, List<T> labelListBeans) {
        this.labelListBeans = labelListBeans;
        mContext = context;
    }

    public int getCount() {
        return labelListBeans.size()+1;
    }

    public Object getItem(int position) {
        if (position<labelListBeans.size()){
            return labelListBeans.get(position);
        }
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_img_video_item, parent, false);
            // construct an item tag
            mHolder = new ViewHolder(convertView);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        if (position != labelListBeans.size()){
            RecommendLabel.DataObjBean.LabelListBean mLabelListBean = labelListBeans.get(position);
        // set name
        mHolder.mLabel.setText(mLabelListBean.getLabel_name());

        // set icon
        String mUrlprefix = PreferenceManager.getInstance().getLabelUrlprefix();
       /* Picasso.get().load(mUrlprefix + mLabelListBean.getBack_url())
//                .config(Bitmap.Config.RGB_565)
                .into(mHolder.mIcon);*/
            Glide.with(mContext).asBitmap().load(mUrlprefix+mLabelListBean.getBack_url())
                   .circleCrop() .placeholder(R.mipmap.icon_search_main_normal).into(mHolder.mIcon);
//        Picasso.get().load(mUrlprefix + mLabelListBean.getBack_url())
//                .placeholder(R.mipmap.icon_search_main_normal).into(mHolder.mIcon);
    }else {
//            mHolder.mIcon.setImageResource(R.mipmap.icon_more_search_tag);
            mHolder.mMore.setVisibility(View.VISIBLE);
            mHolder.mIcon.setVisibility(View.GONE);
            Picasso.get().load(R.mipmap.video_icon_more).into(mHolder.mMore);
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView mIcon;
        TextView mLabel;
        ImageView mMore;
        public ViewHolder(View itemView) {
            this.mLabel = itemView.findViewById(R.id.label);
            this.mIcon = itemView.findViewById(R.id.icon);
            this.mMore = itemView.findViewById(R.id.more);
        }
    }

}