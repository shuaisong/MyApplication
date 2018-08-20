package com.example.lenovo.myapplication.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.lenovo.myapplication.App;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.view.ShowVersionDialog;
import com.example.lenovo.myapplication.base.BaseActivity;
import com.example.lenovo.myapplication.base.BaseFragment;
import com.example.lenovo.myapplication.bean.VersionCode;
import com.example.lenovo.myapplication.bean.msg.FindVersion;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.component.DaggerActivityComponent;
import com.example.lenovo.myapplication.view.fragment.CollectFG;
import com.example.lenovo.myapplication.view.fragment.MineFG;
import com.example.lenovo.myapplication.view.fragment.PhotoFG;
import com.example.lenovo.myapplication.view.fragment.VideoFG;
import com.squareup.leakcanary.RefWatcher;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "Tag";
    private BaseFragment currentFG;
    public static Executor THREAD_POOL_EXECUTOR;
    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new LinkedBlockingQueue<>(128);
    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
        }
    };
    private PhotoFG photoFG;
    private VideoFG videoFG;
    private CollectFG collectFG;
    private MineFG mineFG;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        // StatusBar.statusBarTintColor(this, R.color.colorAccent);
        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.hide();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        RadioButton photo = findViewById(R.id.photo);
        RadioButton mVideo = findViewById(R.id.video);
        RadioButton mCollect = findViewById(R.id.collect);
        RadioButton mMine = findViewById(R.id.mine);
        photo.setOnClickListener(this);
        mVideo.setOnClickListener(this);
        mCollect.setOnClickListener(this);
        mMine.setOnClickListener(this);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onStart() {
        super.onStart();
        selectFG(R.id.photo, R.id.frame_root_fragment);
        int mCPU_COUNT = Runtime.getRuntime().availableProcessors();
        THREAD_POOL_EXECUTOR
                = new ThreadPoolExecutor(mCPU_COUNT + 1, mCPU_COUNT * 2 + 1, 2,
                TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        RefWatcher refWatcher = App.getRefWatcher(this);//1
        refWatcher.watch(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getVersionInfo(FindVersion findVersion) {
        VersionCode.DataObjBean bean = findVersion.getBean();
        String mUpdateContent = bean.getUpdateContent();
        int one = mUpdateContent.lastIndexOf("1");
        int two = mUpdateContent.lastIndexOf("2");
        int three = mUpdateContent.lastIndexOf("3");
        String[] contents = new String[3];
        contents[0] = mUpdateContent.substring(one, two).trim();
        contents[1] = mUpdateContent.substring(two, three).trim();
        contents[2] = mUpdateContent.substring(three).trim();
        Intent mIntent = new Intent(this, ShowVersionDialog.class);
        mIntent.putExtra(Contant.content, contents);
        startActivity(mIntent);
    }
    @Override
    public void onClick(View v) {
        selectFG(v.getId(), R.id.frame_root_fragment);
    }

    private void selectFG(int vId, int id) {
        switch (vId) {
            case R.id.photo:
                if (photoFG == null)
                    photoFG = new PhotoFG();
                add(id, photoFG, "photo");
                break;
            case R.id.video:
                if (videoFG == null)
                    videoFG = new VideoFG();
                add(id, videoFG, "video");
                break;
            case R.id.collect:
                if (collectFG == null)
                    collectFG = new CollectFG();
                add(id, collectFG, "collect");
                break;
            case R.id.mine:
                if (mineFG == null)
                    mineFG = new MineFG();
                add(id, mineFG, "mine");
                break;
            default:
                if (photoFG == null)
                    photoFG = new PhotoFG();
                add(id, photoFG, "photo");
                break;
        }
    }

    private void add(int id, BaseFragment baseFragment, String tag) {
        Log.d(TAG, "add: " + tag);
        FragmentManager mManager = getSupportFragmentManager();
        Fragment fragmentByTag = mManager.findFragmentByTag(tag);
        FragmentTransaction transaction = mManager.beginTransaction();
        if (fragmentByTag != null) baseFragment = (BaseFragment) fragmentByTag;
        if (baseFragment.isAdded()) {
            addOrShowFragment(transaction, id, baseFragment, tag);
        } else {
            Log.d(TAG, "add: " + currentFG + "  " + tag);
            if (currentFG != null && currentFG.isAdded()) {
                transaction.hide(currentFG).add(id, baseFragment, tag).show(baseFragment).commit();
            } else {
                transaction.add(id, baseFragment, tag).commit();
            }
            currentFG = baseFragment;
        }
    }

    private void addOrShowFragment(FragmentTransaction transaction, int layoutId, BaseFragment fragment, String tag) {
        if (currentFG == fragment) {
            return;
        }
        if (!fragment.isAdded()) {
            transaction.hide(currentFG).add(layoutId, fragment, tag).commit();
        } else {
            transaction.hide(currentFG).show(fragment).commit();
        }
        currentFG.setUserVisibleHint(false);
        currentFG = fragment;
        currentFG.setUserVisibleHint(true);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    boolean isExit;

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            handler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    MainHandler handler = new MainHandler(this);

    @Override
    public void finish() {
        super.finish();
    }

    private static class MainHandler extends Handler {
        WeakReference<MainActivity> weakReference;

        MainHandler(MainActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            weakReference.get().isExit = false;
        }
    }

}
