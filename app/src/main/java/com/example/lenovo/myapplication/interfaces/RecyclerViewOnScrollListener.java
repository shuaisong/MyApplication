package com.example.lenovo.myapplication.interfaces;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

/**
 * Created by lenovo on 2018/5/7.
 * auther:lenovo
 * Date：2018/5/7
 */

public class RecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {
    private Context context;

    public RecyclerViewOnScrollListener(Context context) {
        this.context = context;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        Picasso mPicasso = Picasso.get();
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                mPicasso.resumeTag(context);
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                mPicasso.pauseTag(context);
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                mPicasso.pauseTag(context);
                break;
        }
    }
}
