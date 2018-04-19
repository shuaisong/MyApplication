package com.example.lenovo.myapplication.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.squareup.picasso.Picasso;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/4/13.
 */

public class ListAdapter extends BaseAdapter {
    List<HashMap<String, Object>> mapList;
    Context context;
    private static final String TAG = "Tag";

    public ListAdapter(Context context, List<HashMap<String, Object>> mapList) {
        this.mapList = mapList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mapList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.photo_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.name = convertView.findViewById(R.id.photo_list_item_title);
            viewHolder.num = convertView.findViewById(R.id.photo_list_item_num);
            viewHolder.imageView = convertView.findViewById(R.id.photo_list_item_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        viewHolder.imageView.setImageResource((Integer) mapList.get(position).get("pic_url"));
//        Log.d(TAG, "getView: " + mapList.get(position).get("pic_url"));
        Picasso.get().load((String) mapList.get(position).get("pic_url")).into(viewHolder.imageView);
        viewHolder.name.setText((String) mapList.get(position).get("title"));
        viewHolder.num.setText((String) mapList.get(position).get("pic_num"));
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView name;
        TextView num;
    }
}
