package com.example.lenovo.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.DetailImgUrl;
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

public class VPadapter extends PagerAdapter {
    private Context context;
    private List<View> viewList;
    private OnItemClickListener onItemClickListener;
    private  OnItemPageSelectListener onItemPageSelectListener;
    public VPadapter(Context context) {
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
    public void setPosition(int position,int hPosition){
        View mView = viewList.get(position);
        ViewPager viewpager = mView.findViewById(R.id.viewpager);
        if (viewpager != null) {
            viewpager.setCurrentItem(hPosition);
        }
    }

    public void addData(final DetailImgUrl.DataObjBean dataObjBean) {
        final VPViewHolder mViewHolder = new VPViewHolder();
        viewList.add(mViewHolder.view);
        if (viewList.size()>=3) setFirstLoad(false);
        final HVPadapter mHVPadapter = new HVPadapter(context, dataObjBean.getPicUrlList());
        mHVPadapter.setOnitemClickListener(new HVPadapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, DetailImgUrl.DataObjBean.PicUrlListBean bean) {
                onItemClickListener.onItemClick(position,bean);
            }
        });
        mViewHolder.mHViewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int state) {
                Picasso mPicasso = Picasso.get();
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        mPicasso.resumeTag(context);
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        mPicasso.pauseTag(context);
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        mPicasso.pauseTag(context);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                mHVPadapter.loadPic(position);
                if (position < dataObjBean.getPicUrlList().size() - 2)
                    mHVPadapter.loadPic(position + 2);
                int mCollectNum = dataObjBean.getPicUrlList().get(position).getCollection_num();
                String num = String.valueOf(mCollectNum);
                if (mCollectNum > 9999) {
                    BigDecimal b1 = new BigDecimal(mCollectNum);
                    BigDecimal b2 = new BigDecimal(1000);
                    num = b1.divide(b2, 2, BigDecimal.ROUND_DOWN).toString();
                }
                onItemPageSelectListener.onItemPageSelect(position,num);
            }
        });
        mViewHolder.mHViewpager.setOffscreenPageLimit(5);
        mViewHolder.mHViewpager.setAdapter(mHVPadapter);
        int mCollectNum = dataObjBean.getPicUrlList().get(0).getCollection_num();
        String num = String.valueOf(mCollectNum);
        if (mCollectNum > 9999) {
            BigDecimal b1 = new BigDecimal(mCollectNum);
            BigDecimal b2 = new BigDecimal(1000);
            num = b1.divide(b2, 2, BigDecimal.ROUND_DOWN).toString();
        }
        onItemPageSelectListener.onItemPageSelect(0,num);
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
        @BindView(R.id.viewpager)
        ViewPager mHViewpager;
        private final View view;

        VPViewHolder() {
            view = LayoutInflater.from(context).inflate(R.layout.justviewpager, null);
            ButterKnife.bind(this, view);
        }
    }
    public void setOnitemClickListener(OnItemClickListener onitemClickListener){
        this.onItemClickListener = onitemClickListener;
    }
    public void setOnItemPageSelectListener(OnItemPageSelectListener onItemPageSelectListener){
        this.onItemPageSelectListener = onItemPageSelectListener;
    }
   public interface OnItemClickListener{
        void onItemClick(int position, DetailImgUrl.DataObjBean.PicUrlListBean bean);
    }
    public interface OnItemPageSelectListener{
        void onItemPageSelect(int position, String num);
    }
}
