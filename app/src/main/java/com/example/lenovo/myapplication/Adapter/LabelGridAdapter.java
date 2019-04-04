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
import com.example.lenovo.myapplication.bean.LabelBean;

import java.util.List;

/**
 * Created by lenovo on 2018/9/1.
 * auther:lenovo
 * Date：2018/9/1
 */
public class LabelGridAdapter extends BaseAdapter {
    private List<LabelBean.DataObjBean.LabelListBean> labelListBeans;
    private Context mContext;

    public LabelGridAdapter(Context context, List<LabelBean.DataObjBean.LabelListBean> labelListBeans) {
        this.labelListBeans = labelListBeans;
        mContext = context;
    }

    public int getCount() {
        return labelListBeans.size();
    }

    public Object getItem(int position) {
        return labelListBeans.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, parent, false);
            // construct an item tag
            mHolder = new ViewHolder(convertView);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        // set name
        mHolder.mLabel.setText(labelListBeans.get(position).getLabel_name());
        Glide.with(mContext).asBitmap()
                .load(labelListBeans.get(position).getBack_url()).circleCrop().into(mHolder.mIcon);
        // set icon
//        Picasso.get().load( labelListBeans.get(position).getBack_url()).into(mHolder.mIcon);

        return convertView;
    }

    static class ViewHolder {
        ImageView mIcon;
        TextView mLabel;

        public ViewHolder(View itemView) {
            this.mLabel = itemView.findViewById(R.id.label);
            this.mIcon = itemView.findViewById(R.id.icon);
        }
    }

}