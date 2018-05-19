package com.example.lenovo.myapplication.bean;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lenovo.myapplication.Adapter.RecyclerAdapter;

/**
 * Created by lenovo on 2018/5/9.
 * auther:lenovo
 * Date：2018/5/9
 */

public class CustemChangeGridLayoutManagerSpance implements RecyclerAdapter.ChangeGridLayoutManagerSpance {
    private RecyclerView recyclerView;

    public CustemChangeGridLayoutManagerSpance(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void change(int size, boolean isAddFoot) {
        RecyclerView.LayoutManager mLayoutManager = recyclerView.getLayoutManager();
        if (mLayoutManager instanceof GridLayoutManager) {
            final GridLayoutManager mManager = (GridLayoutManager) mLayoutManager;
            mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

                @Override
                public int getSpanSize(int position) {
                    int spanSzie = 1;
                    RecyclerAdapter mAdapter = (RecyclerAdapter) recyclerView.getAdapter();
                    if (position == mAdapter.getDataSize()) {
                        spanSzie = mManager.getSpanCount();
                    }
                    return spanSzie;
                }
            });
        }
    }
}

