package com.example.lenovo.myapplication.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseFragment;
import com.example.lenovo.myapplication.bean.msg.ChangeRow;
import com.example.lenovo.myapplication.utils.PreferenceManager;

import static org.greenrobot.eventbus.EventBus.getDefault;

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

    protected void initView() {
        Switch switch_samll = rootView.findViewById(R.id.switch_small);
        int mRow = PreferenceManager.getInstance().getRow();
        if (mRow == 1) {
            switch_samll.setChecked(false);
        } else switch_samll.setChecked(true);
        switch_samll.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        ChangeRow mRow = new ChangeRow();
        if (isChecked) {
            mRow.setRow(2);
            getDefault().post(mRow);
        }
        if (!isChecked) {
            mRow.setRow(1);
            getDefault().post(mRow);
        }
    }
}
