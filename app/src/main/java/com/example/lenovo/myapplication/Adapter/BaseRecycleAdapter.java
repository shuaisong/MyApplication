package com.example.lenovo.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.interfaces.OnLoadMoreListener;
import com.example.lenovo.myapplication.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER_VIEW = 100000;//footer类型 Item
    static final int TYPE_COMMON_VIEW = 100001;//普通类型 Item
    private static final int TYPE_FOOTER_VIEW = 100002;//footer类型 Item
    private static final int TYPE_EMPTY_VIEW = 100003;//empty view，即初始化加载时的提示View
    private static final int TYPE_NODATE_VIEW = 100004;//初次加载无数据的默认空白view
    private static final int TYPE_RELOAD_VIEW = 100005;//初次加载无数据的可重新加载或提示用户的view

    private OnLoadMoreListener mLoadMoreListener;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status=Contant.normal;//103加载104刷新105正常
    Context mContext;
    List<T> mDatas;
    List headerList;

    public void setmOpenLoadMore(boolean mOpenLoadMore) {
        this.mOpenLoadMore = mOpenLoadMore;
    }

    private boolean mOpenLoadMore;//是否开启加载更多
    private boolean isAutoLoadMore = true;//是否自动加载，当数据不满一屏幕会自动加载
    boolean haveHeader =false;
    private boolean isRemoveEmptyView;
    private View mLoadingView; //分页加载中view
    private View mLoadFailedView; //分页加载失败view
    private View mLoadEndView; //分页加载结束view
    private View mEmptyView; //首次预加载view
    private View mReloadView; //首次预加载失败、或无数据的view
    private RelativeLayout mFooterLayout;//footer view
    private RelativeLayout mHeaderLayout;//mHeaderLayout
    private boolean isReset;//开始重新加载数据

    protected abstract int getViewType(int position, T data);

    BaseRecycleAdapter(Context context, List<T> datas, boolean isOpenLoadMore) {
        mContext = context;
        mDatas = datas == null ? new ArrayList<T>() : datas;
        mOpenLoadMore = isOpenLoadMore;
    }
    BaseRecycleAdapter(Context context, List<T> datas, boolean isOpenLoadMore, boolean haveHeader) {
      this(context,datas,isOpenLoadMore);
      this.haveHeader =haveHeader;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        switch (viewType) {
            case TYPE_HEADER_VIEW:
                if (mHeaderLayout == null) {
                    mHeaderLayout = new RelativeLayout(mContext);
                }
                return ViewHolder.create(mHeaderLayout);
            case TYPE_FOOTER_VIEW:
                if (mFooterLayout == null) {
                    mFooterLayout = new RelativeLayout(mContext);
                }
                return ViewHolder.create(mFooterLayout);
            case TYPE_EMPTY_VIEW:
                return ViewHolder.create(mEmptyView);
            case TYPE_NODATE_VIEW:
                return ViewHolder.create(new View(mContext));
            case TYPE_RELOAD_VIEW:
                return ViewHolder.create(mReloadView);
        }
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        if (mDatas.isEmpty() && mEmptyView != null) {
            return 1;
        }
        if (haveHeader)return mDatas.size()+getFooterViewCount()+1;
        return mDatas.size() + getFooterViewCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.isEmpty()) {
            if (mEmptyView != null && !isRemoveEmptyView) {
                return TYPE_EMPTY_VIEW;
            }

            if (isRemoveEmptyView && mReloadView != null) {
                return TYPE_RELOAD_VIEW;
            } else {
                return TYPE_NODATE_VIEW;
            }
        }

        if (isFooterView(position)) {
            return TYPE_FOOTER_VIEW;
        }
        if (haveHeader){
            if (position==0){
                return TYPE_HEADER_VIEW;
            }else
            return getViewType(position, mDatas.get(position-1));
        }
        return getViewType(position, mDatas.get(position));
    }

    /**
     * 根据positiond得到data
     *
     * @param position position
     * @return position item
     */
    public T getItem(int position) {
        if (mDatas.isEmpty()) {
            return null;
        }
        if (haveHeader){
            if (position!=0)
            return mDatas.get(position-1);else return mDatas.get(0);
        }
        return mDatas.get(position);
    }

    /**
     * 是否是FooterView
     *
     * @param position position
     * @return true 尾
     */
    private boolean isFooterView(int position) {
        return mOpenLoadMore && position >= getItemCount() - 1;
    }
    private boolean isHeaderView(int position){
        return haveHeader&&position==0;
    }
    /**
     * @param viewType
     * @return true 普通布局
     */
    boolean isCommonItemView(int viewType) {
        return viewType != TYPE_EMPTY_VIEW && viewType != TYPE_FOOTER_VIEW
                && viewType != TYPE_NODATE_VIEW && viewType != TYPE_RELOAD_VIEW&&viewType!=TYPE_HEADER_VIEW;
    }
    /**
     * StaggeredGridLayoutManager模式时，FooterView可占据一行
     *
     * @param holder viewholder
     */
    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isFooterView(holder.getLayoutPosition())) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
        if (isHeaderView(holder.getLayoutPosition())){
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

    /**
     * GridLayoutManager模式时， FooterView可占据一行，判断RecyclerView是否到达底部
     *
     * @param recyclerView recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isFooterView(position)) {
                        return gridManager.getSpanCount();
                    }
                    if (isHeaderView(position)){
                        return gridManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
        startLoadMore(recyclerView, layoutManager);
    }


    /**
     * 判断列表是否滑动到底部
     *
     * @param recyclerView  view
     * @param layoutManager layoutManager
     */
    private void startLoadMore(RecyclerView recyclerView, final RecyclerView.LayoutManager layoutManager) {
        if (!mOpenLoadMore || mLoadMoreListener == null) {
            return;
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!isAutoLoadMore && findLastVisibleItemPosition(layoutManager) + 1 == getItemCount()) {
                        scrollLoadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isAutoLoadMore && findLastVisibleItemPosition(layoutManager) + 1 == getItemCount()) {
                    scrollLoadMore();
                } else if (isAutoLoadMore) {
                    isAutoLoadMore = false;
                }
            }
        });
    }

    /**
     * 到达底部开始刷新
     */
    private void scrollLoadMore() {
        if (isReset) {
            return;
        }
        if (mFooterLayout.getChildAt(0) == mLoadingView) {
            if (mLoadMoreListener != null&&status== Contant.normal) {
                mLoadMoreListener.onLoadMore(false);
            }
        }
    }

    private int findLastVisibleItemPosition(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
            return Util.findMax(lastVisibleItemPositions);
        }
        return -1;
    }

    /**
     * 清空footer view
     */
    private void removeFooterView() {
        mFooterLayout.removeAllViews();
    }
    private void removeHeaderView() {
        mHeaderLayout.removeAllViews();
    }

    /**
     * 添加新的footer view
     *
     * @param footerView footerView
     */
    private void addFooterView(View footerView) {
        if (footerView == null) {
            return;
        }
        if (mFooterLayout == null) {
            mFooterLayout = new RelativeLayout(mContext);
        }
        removeFooterView();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mFooterLayout.addView(footerView, params);
    }
    private void addHeaderView(View headerView){
        if (headerView == null) {
            return;
        }
        if (mHeaderLayout == null) {
            mHeaderLayout = new RelativeLayout(mContext);
        }
        removeHeaderView();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        mHeaderLayout.addView(headerView,0, params);
    }
    /**
     * 刷新加载更多的数据
     *
     * @param datas
     */
    public void setLoadMoreData(List<T> datas) {
        int size = mDatas.size();
        mDatas.addAll(datas);
        notifyItemInserted(size);
    }

    /**
     * 下拉刷新，得到的新数据插到原数据起始
     *
     * @param datas
     */
    public void setData(List<T> datas) {
        mDatas.addAll(0, datas);
        notifyDataSetChanged();
    }

    /**
     * 初次加载、或下拉刷新要替换全部旧数据时刷新数据
     *
     * @param datas
     */
    public void setNewData(List<T> datas) {
        if (isReset) {
            isReset = false;
        }
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mDatas.remove(position);
        notifyDataSetChanged();
    }
   public void setHeadView(View headView){
        addHeaderView(headView);
    }
    /**
     * 初始化加载中布局
     *
     * @param loadingView
     */
    private void setLoadingView(View loadingView) {
        mLoadingView = loadingView;
        addFooterView(mLoadingView);
    }

    public void setLoadingView(int loadingId) {
        setLoadingView(Util.inflate(mContext, loadingId));
    }

    /**
     * 初始加载失败布局
     *
     * @param loadFailedView
     */
    private void setLoadFailedView(View loadFailedView) {
        mLoadFailedView = loadFailedView;
    }

    public void setLoadFailedView(int loadFailedId) {
        setLoadFailedView(Util.inflate(mContext, loadFailedId));
    }

    /**
     * 初始化全部加载完成布局
     *
     * @param loadEndView
     */
    private void setLoadEndView(View loadEndView) {
        mLoadEndView = loadEndView;
    }

    public void setLoadEndView(int loadEndId) {
        setLoadEndView(Util.inflate(mContext, loadEndId));
    }

    /**
     * 初始化emptyView
     *
     * @param emptyView
     */
    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }

    /**
     * 移除emptyView
     */
    public void removeEmptyView() {
        isRemoveEmptyView = true;
        notifyDataSetChanged();
    }

    /**
     * 初次预加载失败、或无数据可显示该view，进行重新加载或提示用户无数据
     *
     * @param reloadView reloadView
     */
    public void setReloadView(View reloadView) {
        mReloadView = reloadView;
        isRemoveEmptyView = true;
        notifyDataSetChanged();
    }

    /**
     * 返回 footer view数量
     *
     * @return FooterViewCount
     */
    private int getFooterViewCount() {
        return mOpenLoadMore && !mDatas.isEmpty() ? 1 : 0;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        mLoadMoreListener = loadMoreListener;
    }

    /**
     * 重置adapter，恢复到初始状态
     */
    public void reset() {
        if (mLoadingView != null) {
            addFooterView(mLoadingView);
        }
        isReset = true;
        isAutoLoadMore = true;
        mDatas.clear();
    }
    public boolean isLoadEnd =false;
    /**
     * 数据加载完成
     */
    public void loadEnd() {
        isLoadEnd=true;
        if (mLoadEndView != null) {
            addFooterView(mLoadEndView);
        }
    }

    /**
     * 数据加载失败
     */
    public void loadFailed() {
        addFooterView(mLoadFailedView);
        mLoadFailedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFooterView(mLoadingView);
                if (mLoadMoreListener != null) {
                    mLoadMoreListener.onLoadMore(true);
                }
            }
        });
    }
}
