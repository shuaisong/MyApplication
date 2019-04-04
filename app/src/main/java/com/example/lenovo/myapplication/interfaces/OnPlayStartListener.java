package com.example.lenovo.myapplication.interfaces;


import com.example.lenovo.myapplication.Adapter.ViewHolder;

/**
 */
public interface OnPlayStartListener<T> {
    void onnPlayStartClick(ViewHolder viewHolder, T data, int position);
}
