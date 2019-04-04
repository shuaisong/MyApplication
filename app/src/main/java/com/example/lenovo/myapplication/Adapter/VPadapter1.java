package com.example.lenovo.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.DetailImgUrl;
import com.example.lenovo.myapplication.utils.LogUtil;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/4/12.
 * auther:lenovo
 * Date：2018/4/12
 */

public class VPadapter1 extends PagerAdapter {
    private Context context;
    private List<View> viewList;
    private OnItemClickListener onItemClickListener;
    private OnItemPageSelectListener onItemPageSelectListener;

    public VPadapter1(Context context) {
        this.context = context;
        this.viewList = new ArrayList<>();
    }

    @Override
    public int getCount() {//必须实现
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {//必须实现
        return view == object;
    }

    public void setViewAtFirst(int position) {
        View mView = viewList.get(position);
        ViewPager viewpager = mView.findViewById(R.id.viewpager);
        if (viewpager != null) {
            viewpager.setCurrentItem(0);
        }
    }

    public void setPosition(int position, int hPosition) {
        View mView = viewList.get(position);
        ViewPager viewpager = mView.findViewById(R.id.viewpager);
        if (viewpager != null) {
            viewpager.setCurrentItem(hPosition);
        }
    }

    public void addData(final DetailImgUrl.DataObjBean dataObjBean) {
        final VPViewHolder mViewHolder = new VPViewHolder();
        viewList.add(mViewHolder.mRecycler);
        LogUtil.d(viewList.size());
        if (viewList.size() >= 3) setFirstLoad(false);
        LinearLayoutManager mPagerLayoutManager =
                new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mViewHolder.mRecycler.setLayoutManager(mPagerLayoutManager);

        QDRecyclerViewAdapter mRecyclerViewAdapter = new QDRecyclerViewAdapter();
        mRecyclerViewAdapter.setItemCount(10);
        mViewHolder.mRecycler.setAdapter(mRecyclerViewAdapter);
        int mCollectNum = dataObjBean.getPicUrlList().get(0).getCollection_num();
        String num = String.valueOf(mCollectNum);
        if (mCollectNum > 9999) {
            BigDecimal b1 = new BigDecimal(mCollectNum);
            BigDecimal b2 = new BigDecimal(1000);
            num = b1.divide(b2, 2, BigDecimal.ROUND_DOWN).toString();
        }
        PagerSnapHelper mSnapHelper = new PagerSnapHelper();
        mSnapHelper.attachToRecyclerView( mViewHolder.mRecycler);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View mView = viewList.get(position);
        container.addView(mView);
        return mView;
    }


    private boolean firstLoad;

    public boolean isFirstLoad() {
        return firstLoad;
    }

    public void setFirstLoad(boolean firstLoad) {
        this.firstLoad = firstLoad;
    }

    public class VPViewHolder {
        @BindView(R.id.recycler)
        RecyclerView mRecycler;

        VPViewHolder() {
            View view = LayoutInflater.from(context).inflate(R.layout.vp_recycler, null);
            ButterKnife.bind(this, view);
        }
    }

    public void setOnitemClickListener(OnItemClickListener onitemClickListener) {
        this.onItemClickListener = onitemClickListener;
    }

    public void setOnItemPageSelectListener(OnItemPageSelectListener onItemPageSelectListener) {
        this.onItemPageSelectListener = onItemPageSelectListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, DetailImgUrl.DataObjBean.PicUrlListBean bean);
    }

    public interface OnItemPageSelectListener {
        void onItemPageSelect(int position, String num);
    }
}
