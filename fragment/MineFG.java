package com.example.lenovo.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseFragment;

/**
 * Created by lenovo on 2018/4/19.
 * auther:lenovo
 * Date：2018/4/19
 */

public class MineFG extends BaseFragment implements CompoundButton.OnCheckedChangeListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fg_mine, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        Switch switch_samll = rootView.findViewById(R.id.switch_small);
        switch_samll.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        PhotoFG photo = (PhotoFG) getActivity().getSupportFragmentManager().findFragmentByTag("photo");
        if (isChecked) photo.setSpanCount(2);
        if (!isChecked) photo.setSpanCount(1);
    }
}
