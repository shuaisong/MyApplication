package com.example.lenovo.myapplication.interfaces;


import com.example.lenovo.myapplication.Adapter.ViewHolder;

/**
 * Time: 2016/8/29 10:48
 */
public interface OnSwipeMenuClickListener<T> {
    void onSwipMenuClick(ViewHolder viewHolder, T data, int position);
}
