package com.example.lenovo.myapplication.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseFragment;

/**
 * Created by lenovo on 2018/4/19.
 * auther:lenovo
 * Date：2018/4/19
 */

public class VideoFG extends BaseFragment {

    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fg_video, container, false);
        initView();
        return rootView;
    }

    protected void initView() {
        swipeRefreshLayout = rootView.findViewById(R.id.video_swipe);
    }

    @Override
    public void onResume() {
        super.onResume();
        firstFresh();
        setListener();
    }

    private void firstFresh() {
        swipeRefreshLayout.measure(0, 0);
        swipeRefreshLayout.setRefreshing(false);
    }

    private void setListener() {

    }

}
