package com.example.lenovo.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 2018/4/15.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String TAG = "Tag";
    private Context context;
    private int layout;
    private List<HashMap<String, Object>> mapList;

    public void setmFooterView(View mFooterView) {
        this.mFooterView = mFooterView;
    }

    private View mFooterView;

    public RecyclerAdapter(Context context, List<HashMap<String, Object>> mapList, int layout) {
        this.context = context;
        this.mapList = mapList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashMap<String, Object> photo_info = mapList.get(position);
//        Log.d(TAG, "onBindViewHolder: " + photo_info.get("pic_url"));
        Picasso.with(context).load((String) photo_info.get("pic_url")).into(holder.img);
        holder.title.setText((String) mapList.get(position).get("title"));
        holder.num.setText((String) mapList.get(position).get("pic_num"));
    }

    @Override
    public int getItemCount() {
        return mapList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView num;
        private final ImageView img;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.photo_list_item_image);
//            ImageViewUtil.matchAll(context, img);
            num = (TextView) itemView.findViewById(R.id.photo_list_item_num);
            title = (TextView) itemView.findViewById(R.id.photo_list_item_title);
        }
    }
}
