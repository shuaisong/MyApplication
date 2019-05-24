package com.example.lenovo.myapplication.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.view.KeyEvent;

import com.example.lenovo.myapplication.Contact.LeadContact;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseActivity;
import com.example.lenovo.myapplication.bean.BaseUrl;
import com.example.lenovo.myapplication.bean.FeedBack;
import com.example.lenovo.myapplication.bean.RecommendLabel;
import com.example.lenovo.myapplication.bean.SearchType;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.component.DaggerActivityComponent;
import com.example.lenovo.myapplication.presenter.LeadPresenter;
import com.example.lenovo.myapplication.utils.LogUtil;
import com.example.lenovo.myapplication.utils.MD5Utils;
import com.example.lenovo.myapplication.utils.PreferenceManager;
import com.example.lenovo.myapplication.utils.RSAUtils;
import com.example.lenovo.myapplication.utils.StatusBarUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyPair;
import java.util.Arrays;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/4/26.
 * auther:lenovo
 * Date：2018/4/26
 */

public class LeadActivity extends BaseActivity implements LeadContact.View {
    @Inject
    LeadPresenter presenter;

    @Override
    protected void initData() {
        LogUtil.d(MD5Utils.getMd5String("AA"));
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "XX.txt";
        try {
            LogUtil.d(MD5Utils.md5ForFile(new File(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        KeyPair mKeyPair = RSAUtils.generateKeyPair(512);
        String mS = RSAUtils.encryptDataByPublicKey("AAAAAA".getBytes(), mKeyPair.getPublic());
        LogUtil.d(mS);
        LogUtil.d(RSAUtils.decryptToStrByPrivate(mS, mKeyPair.getPrivate()));
        presenter.initData();
        presenter.checkVersion();
        presenter.feedBack();
        presenter.getSearchKey(Contant.SearchSign1, "1");
        presenter.getSearchKey(Contant.SearchSign2, "2");
        presenter.getRecommend();
//        showFileInfo();
    }

    private void showFileInfo() {
        LogUtil.d("getCacheDir"+getCacheDir().getAbsolutePath());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            LogUtil.d("getCodeCacheDir"+getCodeCacheDir().getAbsolutePath());
        }
        if (getExternalCacheDir()!=null)
            LogUtil.d("getExternalCacheDir"+getExternalCacheDir().getAbsolutePath());
        else LogUtil.d("getExternalCacheDir()==null");
        LogUtil.d("getFilesDir"+getFilesDir().getAbsolutePath());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            LogUtil.d("getExternalMediaDirs"+ Arrays.toString(getExternalMediaDirs()));
        }
        LogUtil.d("getDataDirectory"+ Environment.getDataDirectory().getAbsolutePath());
        LogUtil.d("getDownloadCacheDirectory"+Environment.getDownloadCacheDirectory().getAbsolutePath());
        LogUtil.d("getExternalStorageDirectory"+Environment.getExternalStorageDirectory().getAbsolutePath());
        File mFile = new File(Environment.getExternalStorageDirectory() + File.separator+"MyMM131"+File.separator);
        if (!mFile.exists()){
            boolean mMkDir = mFile.mkdir();
            LogUtil.d("mkDir:"+mMkDir);
        }
        LogUtil.d("getRootDirectory"+Environment.getRootDirectory().getAbsolutePath());
    }

    @Override
    protected void initViews() {
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, false);
        StatusBarUtil.setStatusBarColor(this, Color.TRANSPARENT);
        presenter.attachView(this);
    }

    @Override
    protected void justShowUI() {
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_lead;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode != KeyEvent.KEYCODE_BACK && super.onKeyDown(keyCode, event);
    }

    @Override
    public void start() {

    }

    @Override
    public void compete() {

    }

    @Override
    public void saveData(BaseUrl.DataObjBean.UrlInfoBean bean) {
        PreferenceManager.getInstance().setUrl(bean);
    }
    @Override
    public void showFeedBack(FeedBack.DataObjBean feedBack) {

    }

    @Override
    public void saveSearchKey(SearchType.DataObjBean bean, String type) {
        PreferenceManager.getInstance().saveSearchKey(bean, type);
    }

    @Override
    public void saveRecommend(RecommendLabel.DataObjBean bean) {
        PreferenceManager.getInstance().saveRecommend(bean);
    }
    private int count;
    @Override
    public void feedBack( ) {
        count++;
        LogUtil.d(count);
        if (count>=6){startActivity();}
    }

    private void startActivity() {
        boolean mIsFirst = PreferenceManager.getInstance().getIsFirst();
        Intent intent;
        if (mIsFirst) {
            intent = new Intent(this, WelcomeActivity.class);
        } else {
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        System.gc();
        finish();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();

    }
}