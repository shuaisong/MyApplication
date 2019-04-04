package com.example.lenovo.myapplication.interfaces;


import com.example.lenovo.myapplication.Adapter.ViewHolder;

/**
 */
public interface OnItemClickListener<T> {
    void onItemClick(ViewHolder viewHolder, T data, int position);

}
