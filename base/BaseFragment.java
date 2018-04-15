package com.example.lenovo.myapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myapplication.R;

/**
 * Created by lenovo on 2018/4/12.
 */

public class BaseFragment extends Fragment {

    private static final String TAG = "Tag";
    protected int fgid;
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null)
            fgid = arguments.getInt("FGID");
        Log.d(TAG, "onCreateView: " + fgid);
 /*         Log.d(TAG, "photo: " + R.id.photo);
        Log.d(TAG, "video: " + R.id.video);
        Log.d(TAG, "collect: " + R.id.collect);
        Log.d(TAG, "mine: " + R.id.mine);*/
        switch (fgid) {
            case R.id.photo:
                fgid = R.layout.fg_photo;
                break;
            case R.id.video:
                fgid = R.layout.fg_video;
                break;
            case R.id.collect:
                fgid = R.layout.fg_collect;
                break;
            case R.id.mine:
                fgid = R.layout.fg_mine;
                break;
            default:
                fgid = R.layout.fg_photo;
        }
        //View rootView = inflater.inflate(fgid, container, false);
        rootView = inflater.inflate(fgid, null);
        return rootView;
    }
}