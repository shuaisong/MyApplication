package com.example.lenovo.myapplication.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
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

/**
 * Created by lenovo on 2018/4/12.
 */

public class PhotoFG extends BaseFragment implements ViewPager.OnPageChangeListener {
    PhotoShowHandler handler = new PhotoShowHandler(this);
    private boolean first = true;
    static List<PicListBean> hot_urlInfo;
    static List<PicListBean> new_urlInfo;
    //static ListView list_hot;
    @SuppressLint("StaticFieldLeak")
    static RecyclerView list_re_hot;
    @SuppressLint("StaticFieldLeak")
    static RecyclerView list_new;

    public static void setSpanCount(int spanCount) {
        PhotoFG.spanCount = spanCount;
    }

    private static int spanCount = 2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        initView();
        return rootView;
    }

    private void initView() {
        MainActivity activity = (MainActivity) getActivity();
        ViewPager viewPager = rootView.findViewById(R.id.viewpager);
        List<View> list = new ArrayList<>();
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        @SuppressLint("InflateParams") View page1 = layoutInflater.inflate(R.layout.page1, null);
        @SuppressLint("InflateParams") View page2 = layoutInflater.inflate(R.layout.page2, null);
        list.add(page1);
        list.add(page2);
        VPadapter vPadapter = new VPadapter(list);
        viewPager.setAdapter(vPadapter);
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(this);
        list_new = page1.findViewById(R.id.list_new);
//        list_hot = page2.findViewById(R.id.list_hot);
        list_re_hot = page2.findViewById(R.id.list_re_hot);
        setRecyclerInfo(spanCount);

        String hotPicList = "http://mmapi.yomei.tv/mm131/getHotPicList?lastIndex=-1";
        PhotoShow.showPhoto(getActivity(), handler, hotPicList);
    }

    private void setRecyclerInfo(int spnCount) {
        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(), spnCount);
        GridLayoutManager layoutManager2 = new GridLayoutManager(getActivity(), spnCount);
        int dimension = getResources().getDimensionPixelSize(R.dimen.photo_list_divider_height);
        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(dimension);
        list_re_hot.addItemDecoration(itemDecoration);
        list_re_hot.setLayoutManager(layoutManager1);
        list_new.addItemDecoration(itemDecoration);
        list_new.setLayoutManager(layoutManager2);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (first && position == 0) {
            final String newPicList = "http://mmapi.yomei.tv/mm131/getNewPicList?lastIndex=-1";
            PhotoShow.showPhoto(getActivity(), handler, newPicList);
            first = false;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private static class PhotoShowHandler extends BaseHandler {
        private static final String TAG = "Tag";
        private final WeakReference<PhotoFG> weakReference;
        private RecyclerAdapter recyclerAdapter;

        private PhotoShowHandler(PhotoFG photoFG) {
            this.weakReference = new WeakReference<>(photoFG);
        }

        private RecyclerAdapter getAdapter(List<PicListBean> urlInfo) {
            List<HashMap<String, Object>> hashMaps = new ArrayList<>();
            HashMap<String, Object> map_hot;
            for (int j = 0; j < urlInfo.size(); j++) {
                map_hot = new HashMap<>();
                map_hot.put("title", urlInfo.get(j).getTitle());
                map_hot.put("pic_num", urlInfo.get(j).getPic_num());
                Log.d(TAG, "handleMessage: " + urlInfo.get(j).getDetail1_url());
                map_hot.put("pic_url", "http://img10.mm798.net" + urlInfo.get(j).getDetail1_url());
                hashMaps.add(map_hot);
            }
            if (spanCount == 1) {
                recyclerAdapter = new RecyclerAdapter(weakReference.get().getActivity(), hashMaps, R.layout.photo_list_item1);
            }
            if (spanCount == 2) {
                recyclerAdapter = new RecyclerAdapter(weakReference.get().getActivity(), hashMaps, R.layout.photo_list_item);
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
                        list_re_hot.setAdapter(recyclerAdapter);
                    } else {
                        Gson gson = new Gson();
                        NewPhoto baseUrl = gson.fromJson(showPhoto, NewPhoto.class);
                        new_urlInfo = baseUrl.getDataObj().getNewPicList();
                        recyclerAdapter = getAdapter(new_urlInfo);
                        list_new.setAdapter(recyclerAdapter);
                    }
                }
            }
        }

    }
}
