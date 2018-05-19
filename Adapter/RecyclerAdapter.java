package com.example.lenovo.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myapplication.R;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Contract;

import java.util.HashMap;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Created by lenovo on 2018/4/15.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String TAG = "Tag";
    private static final int TYPE_ITEM = 1, TYPE_FOOT = 2;
    private int layout;

    private List<HashMap<String, Object>> mapList;
    private View footView;
    private int footViewSize = 0;
    private boolean isAddFoot = false;

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public RecyclerAdapter(List<HashMap<String, Object>> mapList, int layout) {
        this.mapList = mapList;
        this.layout = layout;
    }

    public interface ChangeGridLayoutManagerSpance {
        void change(int size, boolean isAddFoot);
    }

    public void setChangeGridLayoutManager(ChangeGridLayoutManagerSpance changeGridLayoutManagerSpance) {
        changeGridLayoutManagerSpance.change(getItemCount() - 1, isAddFoot);
    }

    public void addFoot(View view) {
        footView = view;
        isAddFoot = true;
        footViewSize = 1;
    }

    public void removeFoot() {
        footView = null;
        isAddFoot = false;
        footViewSize = 0;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (footViewSize == 1 && position == getItemCount() - 1) {
            return TYPE_FOOT;
        }
        return TYPE_ITEM;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_FOOT:
                view = footView;
                break;
            case TYPE_ITEM:
            default:
                view = View.inflate(parent.getContext(), layout, null);
                break;
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_ITEM) {
            final HashMap<String, Object> photo_info = mapList.get(position);
            holder.img.setMinimumHeight(Integer.valueOf((String) photo_info.get("cover_height")));
            holder.img.setMinimumWidth(Integer.valueOf((String) photo_info.get("cover_width")));
            Picasso.get().load((String) photo_info.get("pic_url")).into(holder.img, new Callback() {
                @Override
                public void onSuccess() {
                    holder.title.setText((String) photo_info.get("title"));
                    holder.num.setText((String) photo_info.get("pic_num"));
                }

                @Override
                public void onError(Exception e) {
                }
            });
        }
    }

    public int getDataSize() {
        return mapList.size();
    }

    @Override
    public int getItemCount() {
        return mapList.size() + footViewSize;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView num;
        private final ImageView img;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.photo_list_item_image);
//            ImageViewUtil.matchAll(context, img);
            num = itemView.findViewById(R.id.photo_list_item_num);
            title = itemView.findViewById(R.id.photo_list_item_title);
        }
    }
}
