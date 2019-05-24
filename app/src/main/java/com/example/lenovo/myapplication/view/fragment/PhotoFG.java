package com.example.lenovo.myapplication.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.myapplication.Adapter.MyVPAdapter;
import com.example.lenovo.myapplication.App;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseFragment;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.utils.LogUtil;
import com.example.lenovo.myapplication.view.activity.MainActivity;
import com.example.lenovo.myapplication.view.activity.SearchActivity;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * auther:lenovo
 * Date：2018/4/12
 */


public class PhotoFG extends BaseFragment implements ViewPager.OnPageChangeListener {

    private MainActivity mainActivity;

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    private int current_page = 1;


    @Override
    protected void setupFragComponent(AppComponent appComponent) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fg_photo;
    }

    protected void initView() {
        ViewPager viewPager = rootView.findViewById(R.id.viewpager);
        TabLayout tabLayout = rootView.findViewById(R.id.tab);
        final ImageView search = rootView.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        ImgFG hotFg = ImgFG.getInstance(Contant.HOT);
        ImgFG newFg = ImgFG.getInstance(Contant.NEW);
        List<Fragment> list = new ArrayList<>();
        list.add(newFg);
        list.add(hotFg);
        MyVPAdapter vPadapter = new MyVPAdapter(mainActivity.getSupportFragmentManager(), list);
        viewPager.setAdapter(vPadapter);
        viewPager.setCurrentItem(current_page);
       /* tabLayout.addTab(tabLayout.newTab().setText(R.string.photo_new));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.hot));*/
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);

        TabLayout.Tab tabAt0 = tabLayout.getTabAt(0);
        assert tabAt0 != null;
        TabLayout.Tab tabAt1 = tabLayout.getTabAt(1);
        assert tabAt1 != null;
        tabAt0.setText(R.string.photo_new);
        tabAt1.setText(R.string.hot);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
       /* RefWatcher refWatcher = App.getRefWatcher(mainActivity);//1
        refWatcher.watch(this);*/
        super.onDestroy();
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
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity=null;
    }
}
