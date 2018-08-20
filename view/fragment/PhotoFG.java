package com.example.lenovo.myapplication.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myapplication.Adapter.MyVPAdapter;
import com.example.lenovo.myapplication.App;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseFragment;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * auther:lenovo
 * Date：2018/4/12
 */


public class PhotoFG extends BaseFragment implements ViewPager.OnPageChangeListener {

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    private int current_page = 1;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        super.setUserVisibleHint(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fg_photo, container, false);
        initView();
        return rootView;
    }

    protected void initView() {
        ViewPager viewPager = rootView.findViewById(R.id.viewpager);
        TabLayout tabLayout = rootView.findViewById(R.id.tab);
        ImgFG hotFg = ImgFG.getInstance(Contant.HOT);
        ImgFG newFg = ImgFG.getInstance(Contant.NEW);
        List<Fragment> list = new ArrayList<>();
        list.add(newFg);
        list.add(hotFg);
        MyVPAdapter vPadapter = new MyVPAdapter(getActivity().getSupportFragmentManager(), list);
        viewPager.setAdapter(vPadapter);
        viewPager.setCurrentItem(current_page);
        tabLayout.setupWithViewPager(viewPager);

        TabLayout.Tab tabAt0 = tabLayout.getTabAt(0);
        assert tabAt0 != null;
        TabLayout.Tab tabAt1 = tabLayout.getTabAt(1);
        assert tabAt1 != null;
        tabAt0.setText(R.string.photo_new);
        tabAt1.setText(R.string.hot);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = App.getRefWatcher(getActivity());//1
        refWatcher.watch(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurrent_page(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private String getClassName() {
        return getClass().getName();
    }


}
