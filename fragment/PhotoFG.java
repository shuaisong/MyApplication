package com.example.lenovo.myapplication.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myapplication.Adapter.RecyclerAdapter;
import com.example.lenovo.myapplication.Adapter.VPadapter;
import com.example.lenovo.myapplication.MainActivity;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseFragment;
import com.example.lenovo.myapplication.base.BaseHandler;
import com.example.lenovo.myapplication.bean.CustemChangeGridLayoutManagerSpance;
import com.example.lenovo.myapplication.bean.HotPhoto;
import com.example.lenovo.myapplication.bean.NewPhoto;
import com.example.lenovo.myapplication.bean.PicListBean;
import com.example.lenovo.myapplication.bean.RecyclerViewOnScrollListener;
import com.example.lenovo.myapplication.bean.SpaceItemDecoration;
import com.example.lenovo.myapplication.utils.CheckNet;
import com.example.lenovo.myapplication.utils.PhotoShow;
import com.example.lenovo.myapplication.utils.RecyclerViewUtil;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * auther:lenovo
 * Date：2018/4/12
 */


public class PhotoFG extends BaseFragment implements ViewPager.OnPageChangeListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "Tag";
    PhotoShowHandler handler;
    private boolean first = true;
    static List<PicListBean> hot_urlInfo;
    static List<PicListBean> new_urlInfo;
    private RecyclerView list_hot;
    private RecyclerView list_new;
    private SwipeRefreshLayout new_swipe;
    private SwipeRefreshLayout hot_swipe;
    private static boolean firstFresh = true;
    private static int isFresh = 0;
    final static int FRESH_START = 1;
    final static int FRESH_DIONG = 2;
    final static int FRESH_DONE = 3;
    private static int isLoad = 0;
    final static int LOAD_DIONG = 1;
    final static int LOAD_DONE = 2;
    boolean ismLoad = false;
    private View inflate;
    private View inflate1;
    private View loading;
    private View error;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    private int current_page = 1;
    int new_lastIndex = -1;
    int hot_lastIndex = -1;

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
        Log.d(TAG, "setSpanCount: " + list_hot.getAdapter() + "   " + spanCount);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            if (getCurrent_page() == 0) {
                RecyclerViewUtil.get().setSpanCount(list_new, spanCount);
            }
            if (getCurrent_page() == 1) RecyclerViewUtil.get().setSpanCount(list_hot, spanCount);
            return;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    public int getSpanCount() {
        return spanCount;
    }

    private int spanCount = 1;
    private RecyclerAdapter recyclerAdapter_hot;
    private RecyclerAdapter recyclerAdapter_new;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fg_photo, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        MainActivity activity = (MainActivity) getActivity();
        final ViewPager viewPager = rootView.findViewById(R.id.viewpager);
        TabLayout tabLayout = rootView.findViewById(R.id.tab);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        @SuppressLint("InflateParams") View page1 = layoutInflater.inflate(R.layout.page1, null);
        @SuppressLint("InflateParams") View page2 = layoutInflater.inflate(R.layout.page2, null);
        new_swipe = page1.findViewById(R.id.new_swipe_refresh);
        hot_swipe = page2.findViewById(R.id.hot_swipe_refresh);
        list_new = page1.findViewById(R.id.list_new);
        list_hot = page2.findViewById(R.id.list_hot);
        handler = new PhotoShowHandler(this);

        List<View> list = new ArrayList<>();
        list.add(page1);
        list.add(page2);
        VPadapter vPadapter = new VPadapter(list);
        viewPager.setAdapter(vPadapter);
        viewPager.setCurrentItem(current_page);
        viewPager.addOnPageChangeListener(this);

        tabLayout.setupWithViewPager(viewPager);

        TabLayout.Tab tabAt0 = tabLayout.getTabAt(0);
        assert tabAt0 != null;
        TabLayout.Tab tabAt1 = tabLayout.getTabAt(1);
        assert tabAt1 != null;
        tabAt0.setText(R.string.photo_new);
        tabAt1.setText(R.string.hot);
//        list_hot.setHasFixedSize(true);
        RecyclerViewUtil.setLayoutManger(list_hot, spanCount);
        RecyclerViewUtil.setLayoutManger(list_new, spanCount);
        getAdapter();
        inflate = getLayoutInflater().inflate(R.layout.footer_holder, null);
        inflate1 = getLayoutInflater().inflate(R.layout.footer_holder, null);

        recyclerAdapter_hot.addFoot(inflate);
        recyclerAdapter_new.addFoot(inflate1);

        list_hot.setAdapter(recyclerAdapter_hot);
        list_new.setAdapter(recyclerAdapter_new);

        loading = inflate.findViewById(R.id.loading_viewStub);
        error = inflate.findViewById(R.id.network_error_viewStub);
        setVisible(null);
        //下拉刷新
        int color = getResources().getColor(R.color.colorAccent);
        new_swipe.setColorSchemeColors(color);
        hot_swipe.setColorSchemeColors(color);
        new_swipe.setOnRefreshListener(this);
        hot_swipe.setOnRefreshListener(this);
        list_new.addOnScrollListener(new RecyclerViewOnScrollListener(getActivity()) {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                boolean isToFoot = recyclerView.canScrollVertically(1);
                // TODO: 2018/5/7
            }
        });
        recyclerAdapter_new.setChangeGridLayoutManager(new CustemChangeGridLayoutManagerSpance(list_new));
        recyclerAdapter_hot.setChangeGridLayoutManager(new CustemChangeGridLayoutManagerSpance(list_hot));

        list_hot.addOnScrollListener(new RecyclerViewOnScrollListener(getActivity()) {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (isFresh == FRESH_START) {
                    isFresh = FRESH_DIONG;
                }
                if (isLoad == 0 && RecyclerViewUtil.isBottom(recyclerView) && !firstFresh) {
                    setVisible(loading);
                    isLoad = LOAD_DIONG;
//                    Log.d(TAG, "onScrolled:  ");
                    if (CheckNet.isNetworkConnected(getActivity())) {
//                        showPhoto(170, PhotoShow.LOAD_SIGN);
                        setVisible(null);
                    } /*else {
                        setVisible(error);
                        TextView netError = error.findViewById(R.id.net_error);
                        netError.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                    }*/
                }
            }

        });
        hot_swipe.measure(0, 0);
        hot_swipe.setRefreshing(true);

        showPhoto(-1, PhotoShow.FRESH_SIGN);

        isFresh = FRESH_START;

    }


    private void setVisible(@Nullable View view) {
        loading.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
        if (null != view)
            view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurrent_page(position);
        if (first && position == 0) {
            new_swipe.measure(0, 0);
            new_swipe.setRefreshing(true);
            showPhoto(-1, PhotoShow.FRESH_SIGN);
            first = false;
        }
        if (position == 0)
            RecyclerViewUtil.get().setSpanCount(list_new, spanCount);
        if (position == 1)
            RecyclerViewUtil.get().setSpanCount(list_hot, spanCount);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onRefresh() {
        showPhoto(-1, PhotoShow.FRESH_SIGN);
    }

    private void showPhoto(int index, String sign) {
//        Log.d(TAG, "showPhoto: "+getCurrent_page());
        if (getCurrent_page() == 1) {
            String hotPicList = "http://mmapi.yomei.tv/mm131/getHotPicList?lastIndex=";
            PhotoShow.showPhoto(getActivity(), handler, hotPicList, index, sign);
        }
        if (getCurrent_page() == 0) {
            String newPicList = "http://mmapi.yomei.tv/mm131/getNewPicList?lastIndex=";
            PhotoShow.showPhoto(getActivity(), handler, newPicList, index, sign);
        }
    }


    private void getAdapter() {
        if (getSpanCount() == 1) {
            recyclerAdapter_hot = new RecyclerAdapter(hashMaps_hot, R.layout.photo_list_item1);
            recyclerAdapter_new = new RecyclerAdapter(hashMaps_new, R.layout.photo_list_item1);
        }
        if (getSpanCount() == 2) {
            recyclerAdapter_hot = new RecyclerAdapter(hashMaps_hot, R.layout.photo_list_item2);
            recyclerAdapter_new = new RecyclerAdapter(hashMaps_new, R.layout.photo_list_item1);
        }
    }

    List<HashMap<String, Object>> hashMaps_hot = new ArrayList<>();
    List<HashMap<String, Object>> hashMaps_new = new ArrayList<>();

    private static class PhotoShowHandler extends BaseHandler {
        private final WeakReference<PhotoFG> weakReference;

        private PhotoShowHandler(PhotoFG photoFG) {
            this.weakReference = new WeakReference<>(photoFG);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PhotoFG mPhotoFG = weakReference.get();
            if (weakReference.get() == null) {
                return;
            }
            if (msg.what == 200) {
                String showPhoto = msg.getData().getString("showPhoto", "");
                if (showPhoto != null) {
                    Gson gson = new Gson();
                    if (showPhoto.contains("hotPicList")) {
                        HotPhoto baseUrl = gson.fromJson(showPhoto, HotPhoto.class);
                        hot_urlInfo = baseUrl.getDataObj().getHotPicList();
                        if (mPhotoFG.new_lastIndex != baseUrl.getDataObj().getLastIndex()) {
                            mPhotoFG.new_lastIndex = baseUrl.getDataObj().getLastIndex();
                            RecyclerViewUtil.get().updateList(mPhotoFG.recyclerAdapter_hot, mPhotoFG.hashMaps_hot, hot_urlInfo, msg.arg1 == -1 ? 1 : 2);
                        }
                        mPhotoFG.hot_swipe.setRefreshing(false);
                    } else {
                        NewPhoto baseUrl = gson.fromJson(showPhoto, NewPhoto.class);
                        new_urlInfo = baseUrl.getDataObj().getNewPicList();
                        if (mPhotoFG.hot_lastIndex != baseUrl.getDataObj().getLastIndex()) {
                            mPhotoFG.new_lastIndex = baseUrl.getDataObj().getLastIndex();
                            RecyclerViewUtil.get().updateList(mPhotoFG.recyclerAdapter_new, mPhotoFG.hashMaps_new, new_urlInfo, msg.arg1 == -1 ? 1 : 2);
                        }
                        mPhotoFG.new_swipe.setRefreshing(false);
                    }
                    isFresh = FRESH_DONE;
                    firstFresh = false;
                }
            }
        }

    }
}
