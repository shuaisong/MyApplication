package com.example.lenovo.myapplication.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovo.myapplication.App;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.utils.CheckNet;
import com.example.lenovo.myapplication.utils.PreferenceManager;
import com.example.lenovo.myapplication.utils.ToastUtil;

import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/4/19.
 * auther:lenovo
 * Date：2018/4/19
 */

@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    private boolean checkNet() {
        if (!CheckNet.getIntance().isNetworkConnected(this)) {
            ToastUtil.show(getString(R.string.connect_failed));
            return false;
        }
        if (CheckNet.isVpnUsed() || CheckNet.isWifiProxy(this)) {
            ToastUtil.show(getString(R.string.proxy_connect));
            return false;
        }
        return true;
    }
    @Override
    public int checkSelfPermission(String permission) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission_group.STORAGE) != PackageManager.PERMISSION_GRANTED) {
        } else {

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
        setContentView(getContentView());
        ButterKnife.bind(this);
        setupActivityComponent(App.getInstance().getAppComponent());
        PreferenceManager.init(this);
        initViews();
        if (checkNet())
            initData();
        else justShowUI();
    }

    protected void justShowUI() {
    }

    protected abstract void initData();

    protected abstract void initViews();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    public abstract int getContentView();

}
