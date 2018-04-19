package com.example.lenovo.myapplication.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.myapplication.Adapter.RecyclerAdapter;
import com.example.lenovo.myapplication.Adapter.VPadapter;
import com.example.lenovo.myapplication.MainActivity;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseFragment;
import com.example.lenovo.myapplication.base.BaseHandler;
import com.example.lenovo.myapplication.bean.HotPhoto;
import com.example.lenovo.myapplication.bean.NewPhoto;
import com.example.lenovo.myapplication.bean.PicListBean;
import com.example.lenovo.myapplication.bean.SpaceItemDecoration;
import com.example.lenovo.myapplication.utils.PhotoShow;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.lenovo.myapplication.R.mipmap.icon_network_error;

/**
 * Created by lenovo on 2018/4/12.
 */


public class PhotoFG extends BaseFragment implements ViewPager.OnPageChangeListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "Tag";
    PhotoShowHandler handler = new PhotoShowHandler(this);
    private boolean first = true;
    static List<PicListBean> hot_urlInfo;
    static List<PicListBean> new_urlInfo;
    //static ListView list_hot;
    @SuppressLint("StaticFieldLeak")
    static RecyclerView list_hot;
    @SuppressLint("StaticFieldLeak")
    static RecyclerView list_new;
    private TabLayout tabLayout;
    static SwipeRefreshLayout new_swipe;
    static SwipeRefreshLayout hot_swipe;
    private static ViewStub viewStub;
    private static boolean isFresh = true;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    private int current_page = 1;
    static int new_lastIndex = -1;
    static int hot_lastIndex = -1;

    private String hotPicList = "http://mmapi.yomei.tv/mm131/getHotPicList?lastIndex=";

    private String newPicList = "http://mmapi.yomei.tv/mm131/getNewPicList?lastIndex=";

    public static void setSpanCount(int spanCount) {
        PhotoFG.spanCount = spanCount;
    }

    private static int spanCount = 2;
    private static RecyclerAdapter recyclerAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        initView();
        return rootView;
    }

    private void initView() {
        MainActivity activity = (MainActivity) getActivity();
        final ViewPager viewPager = rootView.findViewById(R.id.viewpager);
        tabLayout = rootView.findViewById(R.id.tab);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        @SuppressLint("InflateParams") View page1 = layoutInflater.inflate(R.layout.page1, null);
        @SuppressLint("InflateParams") View page2 = layoutInflater.inflate(R.layout.page2, null);
        new_swipe = page1.findViewById(R.id.new_swipe_refresh);
        hot_swipe = page2.findViewById(R.id.hot_swipe_refresh);
        list_new = page1.findViewById(R.id.list_new);
        list_hot = page2.findViewById(R.id.list_hot);
        viewStub = page2.findViewById(R.id.pic_stub);

        List<View> list = new ArrayList<>();
        list.add(page1);
        list.add(page2);
        VPadapter vPadapter = new VPadapter(list);
        viewPager.setAdapter(vPadapter);
        viewPager.setCurrentItem(current_page);
        viewPager.addOnPageChangeListener(this);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText(R.string.photo_new);
        tabLayout.getTabAt(1).setText(R.string.hot);
        //下拉加载
        int color = getResources().getColor(R.color.colorAccent);
        new_swipe.setColorSchemeColors(color);
        hot_swipe.setColorSchemeColors(color);
        new_swipe.setOnRefreshListener(this);
        hot_swipe.setOnRefreshListener(this);

        //上拉加载
        list_hot.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                Log.d(TAG, "onScrollStateChanged: " + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isToFoot = recyclerView.canScrollVertically(1);
                Log.d(TAG, "onScrolled: " + isFresh);
                if (!isToFoot && !isFresh) {
                    Toast.makeText(getActivity(), "上拉加载", Toast.LENGTH_SHORT).show();
                    if (viewPager.getCurrentItem() == 0)
                        showPhoto(3853, PhotoShow.LOAD_SIGN_NEW);
                    else showPhoto(170, PhotoShow.LOAD_SIGN);
                }
            }
        });
//        list_hot = page2.findViewById(R.id.list_hot);

        setRecyclerInfo(spanCount);

        showPhoto(-1, PhotoShow.FRESH_SIGN);
    }

    private void setRecyclerInfo(int spnCount) {
        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(), spnCount);
        GridLayoutManager layoutManager2 = new GridLayoutManager(getActivity(), spnCount);
        int dimension = getResources().getDimensionPixelSize(R.dimen.photo_list_divider_height);
        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(dimension);
        list_hot.addItemDecoration(itemDecoration);
        list_hot.setLayoutManager(layoutManager1);
        list_new.addItemDecoration(itemDecoration);
        list_new.setLayoutManager(layoutManager2);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (first && position == 0) {
            setCurrent_page(position);
            showPhoto(-1, PhotoShow.FRESH_SIGN);
            first = false;
        }
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
            PhotoShow.showPhoto(getActivity(), handler, hotPicList + index, sign);
        }
        if (getCurrent_page() == 0) {
            PhotoShow.showPhoto(getActivity(), handler, newPicList + index, sign);
        }
    }


    private static class PhotoShowHandler extends BaseHandler {
        private static final String TAG = "Tag";
        private final WeakReference<PhotoFG> weakReference;
        List<HashMap<String, Object>> hashMaps = new ArrayList<>();

        private PhotoShowHandler(PhotoFG photoFG) {
            this.weakReference = new WeakReference<>(photoFG);
        }

        private RecyclerAdapter getAdapter(List<PicListBean> urlInfo) {
            HashMap<String, Object> map;
            for (int j = 0; j < urlInfo.size(); j++) {
                map = new HashMap<>();
                map.put("title", urlInfo.get(j).getTitle());
                map.put("pic_num", urlInfo.get(j).getPic_num());
                // Log.d(TAG, "handleMessage: " + urlInfo.get(j).getDetail1_url());
                map.put("pic_url", "http://img10.mm798.net" + urlInfo.get(j).getDetail1_url());
                hashMaps.add(map);
            }
            if (spanCount == 1) {
                recyclerAdapter = new RecyclerAdapter(hashMaps, R.layout.photo_list_item1);
            }
            if (spanCount == 2) {
                recyclerAdapter = new RecyclerAdapter(hashMaps, R.layout.photo_list_item);
            }
            return recyclerAdapter;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (weakReference.get() == null) {
                return;
            }
            if (msg.what == 200) {
                String showPhoto = msg.getData().getString("showPhoto", "");
                if (showPhoto != null) {
                    if (showPhoto.contains("hotPicList")) {
                        Gson gson = new Gson();
                        HotPhoto baseUrl = gson.fromJson(showPhoto, HotPhoto.class);
                        hot_urlInfo = baseUrl.getDataObj().getHotPicList();
                        recyclerAdapter = getAdapter(hot_urlInfo);
                        list_hot.setAdapter(recyclerAdapter);
                        isFresh = false;
                        hot_swipe.setRefreshing(false);
                        hot_lastIndex = baseUrl.getDataObj().getLastIndex();
                    } else {
                        Gson gson = new Gson();
                        NewPhoto baseUrl = gson.fromJson(showPhoto, NewPhoto.class);
                        new_urlInfo = baseUrl.getDataObj().getNewPicList();
                        recyclerAdapter = getAdapter(new_urlInfo);
                        list_new.setAdapter(recyclerAdapter);
                        isFresh = false;
                        new_swipe.setRefreshing(false);
                        new_lastIndex = baseUrl.getDataObj().getLastIndex();
                    }
                }
            } else {
                View inflate = viewStub.inflate();
                ImageView iv_pic = (ImageView) inflate.findViewById(R.id.iv_pic);
                iv_pic.setImageResource(icon_network_error);
            }
        }

    }
}
