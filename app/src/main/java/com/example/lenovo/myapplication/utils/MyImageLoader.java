package com.example.lenovo.myapplication.utils;

import android.content.Context;
import android.widget.ImageView;

import com.example.lenovo.myapplication.R;
import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by lenovo on 2018/8/26.
 * auther:lenovo
 * Date：2018/8/26
 */
public class MyImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Picasso.get().load((String) path).placeholder(R.mipmap.video_default).into(imageView);
        LogUtil.d("displayImage");
    }
}
