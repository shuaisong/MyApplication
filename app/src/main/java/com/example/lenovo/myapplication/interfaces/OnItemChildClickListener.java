package com.example.lenovo.myapplication.interfaces;


import com.example.lenovo.myapplication.Adapter.ViewHolder;

/**
 */
public interface OnItemChildClickListener<T> {
    void onItemChildClick(ViewHolder viewHolder, T data, int position);
}
