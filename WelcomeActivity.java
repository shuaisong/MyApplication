package com.example.lenovo.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.lenovo.myapplication.Adapter.VPadapter;
import com.example.lenovo.myapplication.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

public class WelcomeActivity extends BaseActivity {
    private static final String TAG = "Tag";
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
        ActionBar mBar = getSupportActionBar();
        assert mBar != null;
        getSupportActionBar().hide();
        initView();
    }

    private void initView() {
        Log.d(TAG, "initView: ");
        ViewPager viewPager = findViewById(R.id.welcome_pager);
        View page1 = getLayoutInflater().inflate(R.layout.welcom1, null);
        View page2 = getLayoutInflater().inflate(R.layout.welcom2, null);
        View page3 = getLayoutInflater().inflate(R.layout.welcom3, null);
        View page4 = getLayoutInflater().inflate(R.layout.welcom4, null);
        btn_start = page4.findViewById(R.id.btn_start);

        ArrayList<View> views = new ArrayList<>();
        views.add(page1);
        views.add(page2);
        views.add(page3);
        views.add(page4);
        VPadapter vPadapter = new VPadapter(views);
        viewPager.setAdapter(vPadapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences isfirst = getSharedPreferences("isfirst", MODE_PRIVATE);
                SharedPreferences.Editor edit = isfirst.edit();
                edit.putBoolean("isFirst", false).apply();
                Logger.d("first using");
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}