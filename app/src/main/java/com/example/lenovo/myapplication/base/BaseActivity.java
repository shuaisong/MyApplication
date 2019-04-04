package com.example.lenovo.myapplication.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovo.myapplication.App;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.utils.CheckNet;
import com.example.lenovo.myapplication.utils.PreferenceManager;
import com.example.lenovo.myapplication.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2018/4/19.
 * auther:lenovo
 * Date：2018/4/19
 */

@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder bind;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    private boolean checkNet() {
        if (CheckNet.getIntance().isNetworkConnected()) {
            ToastUtil.show(getString(R.string.connect_failed));
            return false;
        }
        /*if (CheckNet.isVpnUsed() || CheckNet.isWifiProxy(this)) {
            ToastUtil.show(getString(R.string.proxy_connect));
            return false;
        }*/
        return true;
    }
    @Override
    public int checkSelfPermission(String permission) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission_group.STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission_group.STORAGE},101);
        }
        return super.checkSelfPermission(permission);
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return super.shouldShowRequestPermissionRationale(permission);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modifyStatus();
        setContentView(getContentView());
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar!=null)mActionBar.hide();
        bind = ButterKnife.bind(this);
        setupActivityComponent(App.getInstance().getAppComponent());
        PreferenceManager.init(this);
        initViews();
        initData();
    }

    protected void modifyStatus() {

    }

    protected void justShowUI() {
    }

    protected abstract void initData();

    protected abstract void initViews();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    public abstract int getContentView();

}
