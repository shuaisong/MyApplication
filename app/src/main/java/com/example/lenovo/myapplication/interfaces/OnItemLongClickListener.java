package com.example.lenovo.myapplication.interfaces;


import com.example.lenovo.myapplication.Adapter.ViewHolder;

/**
 */
public interface OnItemLongClickListener<T> {
    void onItemLongClick(ViewHolder viewHolder, T data, int position);
}
