package com.example.lenovo.myapplication.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.myapplication.Adapter.LabelGridAdapter;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.LabelBean;
import com.example.lenovo.myapplication.request.CallBack;
import com.example.lenovo.myapplication.utils.StatusBarUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.mode.CacheMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LabelActivity extends AppCompatActivity {

    @BindView(R.id.et_search)
    CustomEditText mEtSearch;
    @BindView(R.id.search)
    TextView mSearch;
    @BindView(R.id.toolbar)
    LinearLayout mToolbar;
    @BindView(R.id.label)
    TextView mLabel;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, false);
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.colorAccent));

        ButterKnife.bind(this);
        initData();
    }

    private void initView(int type) {
        final Drawable mDrawable_left;
        if (type == 1) mDrawable_left = getResources().getDrawable(R.mipmap.icon_search_photo);
        else mDrawable_left = getResources().getDrawable(R.mipmap.icon_search_title_video);
        mDrawable_left.setBounds(0, 0, mDrawable_left.getMinimumWidth(), mDrawable_left.getMinimumHeight());
        final Drawable mDrawable_right = getResources().getDrawable(R.mipmap.icon_close_video);
        mDrawable_right.setBounds(0, 0, mDrawable_right.getMinimumWidth(), mDrawable_right.getMinimumHeight());
        mEtSearch.setCompoundDrawables(mDrawable_left, null, null, null);
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0) {
                    mEtSearch.setCompoundDrawables(mDrawable_left, null, mDrawable_right, null);
                } else {
                    mEtSearch.setCompoundDrawables(mDrawable_left, null, null, null);
                }
            }
        });
    }

    private void initData() {
        int mType = getIntent().getIntExtra("type", 1);
        initView(mType);
        String type = mType == 1 ? getString(R.string.privatePhoto) : getString(R.string.index_video);
        String search_type = mType == 1 ? getString(R.string.search_img) : getString(R.string.search_video);
        mEtSearch.setHint(search_type);
        mLabel.setText(String.format("%s标签", type));
        getLabelList(mType);
    }

    private void getLabelList(int type) {
        ViseHttp.GET(Contant.getLabelList).addHeader(Contant.sign, type == 1 ? Contant.getLabelList1Sign : Contant.getLabelList2Sign)
                .addHeader(Contant.token, Contant.tokenValue).cacheMode(CacheMode.FIRST_CACHE)
                .addParam("labelType", String.valueOf(type)).addParam("pageSize", "48")
                .addParam("lastId", "-1").request(new CallBack<LabelBean>() {
            @Override
            public void onSuccess(LabelBean labelBean) {
                showLabel(labelBean);
            }
        });
    }

    private void showLabel(LabelBean labelBean) {
        List<LabelBean.DataObjBean.LabelListBean> mLabelList = labelBean.getDataObj().getLabelList();
        List<View> vp_items = new ArrayList<>(2);
        int vp_size = mLabelList.size() / 16 + 1;
        for (int i = 0; i < vp_size; i++) {
            View vp_item = LayoutInflater.from(this).inflate(R.layout.lab_vp_item, mViewpager, false);
            GridView mGridView = vp_item.findViewById(R.id.gridView);
            List<LabelBean.DataObjBean.LabelListBean> mLabelListBeans;
            if (i != vp_size - 1)
                mLabelListBeans = mLabelList.subList(i * 16, (i + 1) * 16);
            else mLabelListBeans = mLabelList.subList(i * 16, mLabelList.size());
            LabelGridAdapter mLabelGridAdapter = new LabelGridAdapter(this, mLabelListBeans);
            mGridView.setAdapter(mLabelGridAdapter);
            vp_items.add(vp_item);
        }
        VpAdapter mVpAdapter = new VpAdapter(vp_items);
        mViewpager.setAdapter(mVpAdapter);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    class VpAdapter extends PagerAdapter {
        List<View> views;

        public VpAdapter(List<View> views) {
            this.views = views;
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return views.size();
        }


        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }
    }
}
