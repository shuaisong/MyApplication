package com.example.lenovo.myapplication.bean;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;

/**
 * Created by lenovo on 2018/4/15.
 * auther:lenovo
 * Date：2018/4/15
 */

public class SpaceItemDecoration extends ItemDecoration {
    private int mSpace;

    public SpaceItemDecoration(int mSpace) {
        this.mSpace = mSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
        int mSpanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
        if (mSpanCount == 1)
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = mSpace;
            }
        if (mSpanCount == 2) {
            if (parent.getChildAdapterPosition(view) == 0 | parent.getChildAdapterPosition(view) == 1) {
                outRect.top = mSpace;
            }
        }
    }
}
