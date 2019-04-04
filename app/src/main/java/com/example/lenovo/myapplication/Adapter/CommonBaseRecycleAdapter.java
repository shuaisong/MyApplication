package com.example.lenovo.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myapplication.interfaces.OnItemChildClickListener;
import com.example.lenovo.myapplication.interfaces.OnItemClickListener;
import com.example.lenovo.myapplication.interfaces.OnItemLongClickListener;
import com.example.lenovo.myapplication.interfaces.OnSwipeMenuClickListener;
import com.example.lenovo.myapplication.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 */
public abstract class CommonBaseRecycleAdapter<T> extends BaseRecycleAdapter<T> {
    private OnItemClickListener<T> mItemClickListener;
    private OnItemLongClickListener<T> mItemLongClickListener;


    private ArrayList<Integer> mViewId = new ArrayList<>();
    private ArrayList<OnSwipeMenuClickListener<T>> mListener = new ArrayList<>();

    private ArrayList<Integer> mItemChildIds = new ArrayList<>();
    private ArrayList<OnItemChildClickListener<T>> mItemChildListeners = new ArrayList<>();

    public CommonBaseRecycleAdapter(Context context, List<T> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }
    public CommonBaseRecycleAdapter(Context context, List<T> datas, boolean isOpenLoadMore,boolean haveheader) {
        super(context, datas, isOpenLoadMore,haveheader);
    }
    protected abstract void convert(ViewHolder holder, T data, int position);

    protected abstract int getItemLayoutId();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (isCommonItemView(viewType)) {
            View view = LayoutInflater.from(mContext).inflate(getItemLayoutId(), parent, false);
            return ViewHolder.create(view);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        if (isCommonItemView(viewType)) {
            bindCommonItem(holder, position);
        }
    }
    protected void convertHeadView(ViewHolder holder,int position){}
     void bindCommonItem(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        if (haveHeader){
            if (position!=0){
                convert(viewHolder, mDatas.get(position-1), position);
            }else{
                convertHeadView(viewHolder,position);
            }
        }else
        convert(viewHolder, mDatas.get(position), position);

        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null) {
                    if (haveHeader ){
                        if (position!=0)
                        mItemClickListener.onItemClick(viewHolder, mDatas.get(position-1), position);
                    }else
                    mItemClickListener.onItemClick(viewHolder, mDatas.get(position), position);
                }
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mItemLongClickListener != null) {
                    if (haveHeader){
                        if (position!=0){
                            mItemLongClickListener.onItemLongClick(viewHolder, mDatas.get(position-1), position);
                        }
                    }else
                    mItemLongClickListener.onItemLongClick(viewHolder, mDatas.get(position), position);
                }
                return true;
            }
        });
        for (int i = 0; i < mItemChildIds.size(); i++) {
            final int tempI = i;
            View mViewById = viewHolder.getConvertView().findViewById(mItemChildIds.get(i));
            if (mViewById != null) {
                mViewById.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtil.d("OnClickListener"+position);
                        if (haveHeader){
                            mItemChildListeners.get(tempI).onItemChildClick(viewHolder, mDatas.get(position-1), position);
                        }else
                        mItemChildListeners.get(tempI).onItemChildClick(viewHolder, mDatas.get(position), position);
                    }
                });
            }
        }

        if (mViewId.size() > 0 && mListener.size() > 0 && viewHolder.getSwipeView() != null) {
            ViewGroup swipeView = (ViewGroup) viewHolder.getSwipeView();

            for (int i = 0; i < mViewId.size(); i++) {
                final int tempI = i;
                swipeView.findViewById(mViewId.get(i)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.get(tempI).onSwipMenuClick(viewHolder, mDatas.get(position), position);
                    }
                });
            }
        }
    }

    @Override
    protected int getViewType(int position, T data) {
        return TYPE_COMMON_VIEW;
    }

    public void setOnItemClickListener(OnItemClickListener<T> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> itemLongClickListener) {
        mItemLongClickListener = itemLongClickListener;
    }

    public void setOnSwipMenuClickListener(int viewId, OnSwipeMenuClickListener<T> swipeMenuClickListener) {
        mViewId.add(viewId);
        mListener.add(swipeMenuClickListener);
    }

    public void setOnItemChildClickListener(int viewId, OnItemChildClickListener<T> itemChildClickListener) {
        mItemChildIds.add(viewId);
        mItemChildListeners.add(itemChildClickListener);
    }

}
