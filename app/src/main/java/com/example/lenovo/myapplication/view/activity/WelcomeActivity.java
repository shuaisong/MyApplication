package com.example.lenovo.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.utils.LogUtil;
import com.example.lenovo.myapplication.utils.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private ImageView btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        // StatusBar.statusBarTintColor(this, R.color.colorAccent);
        initView();
    }

    private void initView() {

        ViewPager viewPager = findViewById(R.id.welcome_pager);
        LayoutInflater mInflater = LayoutInflater.from(this);
        int[] imgs = {R.mipmap.welcome_1, R.mipmap.welcome_2, R.mipmap.welcome_3};
        ArrayList<View> views = new ArrayList<>();

        for (int i=0;i<3;i++){
            ImageView mImageView = (ImageView) mInflater.inflate(R.layout.welcom_item, viewPager,false);
            mImageView.setImageResource(imgs[i]);
            views.add(mImageView);
        }
        View page4 = mInflater.inflate(R.layout.welcom4, viewPager,false);
        btn_start = page4.findViewById(R.id.btn_start);

        views.add(page4);
        VPAdapter vPadapter = new VPAdapter(views);
        viewPager.setAdapter(vPadapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getInstance().setIsFirst(false);
                LogUtil.d("first using");
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private class VPAdapter extends PagerAdapter {
        List<View> list;

        private VPAdapter(List<View> list) {
            this.list = list;
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return list.size();
        }

        /**
         * Determines whether a page View is associated with a specific key object
         * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
         * required for a PagerAdapter to function properly.
         *
         * @param view   Page View to check for association with <code>object</code>
         * @param object Object to check for association with <code>view</code>
         * @return true if <code>view</code> is associated with the key object <code>object</code>
         */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        /**
         * Remove a page for the given position.  The adapter is responsible
         * for removing the view from its container, although it only must ensure
         * this is done by the time it returns from {@link #finishUpdate(ViewGroup)}.
         *
         * @param container The containing View from which the page will be removed.
         * @param position  The page position to be removed.
         * @param object    The same object that was returned by
         *                  {@link #instantiateItem(View, int)}.
         */
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        /**
         * Create the page for the given position.  The adapter is responsible
         * for adding the view to the container given here, although it only
         * must ensure this is done by the time it returns from
         * {@link #finishUpdate(ViewGroup)}.
         *
         * @param container The containing View in which the page will be shown.
         * @param position  The page position to be instantiated.
         * @return Returns an Object representing the new page.  This does not
         * need to be a View, but can be some other container of the page.
         */
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }
    }
}