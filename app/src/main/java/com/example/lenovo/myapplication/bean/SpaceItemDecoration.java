package com.example.lenovo.myapplication.bean;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by lenovo on 2018/4/15.
 * auther:lenovo
 * Date：2018/4/15
 */

public class SpaceItemDecoration extends ItemDecoration {
    private int mSpace;

    public SpaceItemDecoration(float mSpace) {
        this.mSpace = (int) mSpace;
    }

    public SpaceItemDecoration(int mSpace) {
        this.mSpace = mSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int mPosition = parent.getChildAdapterPosition(view);
        RecyclerView.LayoutManager mLayoutManager = parent.getLayoutManager();
        if (mLayoutManager instanceof GridLayoutManager) {
            outRect.left = mSpace;
            outRect.right = mSpace;
            outRect.bottom = mSpace;
            int mSpanCount = ((GridLayoutManager) mLayoutManager).getSpanCount();
            if (mSpanCount == 1)
                if (mPosition == 0) {
                    outRect.top = mSpace;
                }
            if (mSpanCount >= 2) {
                if (mPosition == 0 | mPosition == 1) {
                    outRect.top = mSpace;
                }
            }
        }
        if (mLayoutManager instanceof LinearLayoutManager) {
            outRect.bottom = mSpace;
            outRect.top = mSpace;
        }
        if (mLayoutManager instanceof StaggeredGridLayoutManager){

            int mSpanCount = ((StaggeredGridLayoutManager) mLayoutManager).getSpanCount();
            int column = mPosition % mSpanCount; // item column
            outRect.left = mSpace - column * mSpace / mSpanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * mSpace / mSpanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (mPosition < mSpanCount) { // top edge
                outRect.top = mSpace;
            }
            outRect.bottom = mSpace; // item bottom
        }
    }
}
