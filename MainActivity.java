package com.example.lenovo.myapplication;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.example.lenovo.myapplication.base.BaseFragment;
import com.example.lenovo.myapplication.base.BaseHandler;
import com.example.lenovo.myapplication.bean.BaseUrl;
import com.example.lenovo.myapplication.fragment.PhotoFG;
import com.example.lenovo.myapplication.utils.Initdata;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Tag";
    private RadioButton photo;
    private RadioButton video;
    private RadioButton collect;
    private RadioButton mine;
    private BaseFragment currentFG;
    private BaseFragment baseFragment;
    private FragmentManager manager;
    private static int CPU_COUNT;
    public static Executor THREAD_POOL_EXECUTOR;
    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new LinkedBlockingQueue<Runnable>(128);
    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
        }
    };
    BaseHandler inithandler = new InitDataHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //透明状态栏
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        // StatusBar.statusBarTintColor(this, R.color.colorAccent);
        initView();
        photo.setChecked(true);
        baseFragment = new PhotoFG();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_root_fragment, baseFragment, "photo").commit();
        currentFG = baseFragment;
        CPU_COUNT = Runtime.getRuntime().availableProcessors();
        THREAD_POOL_EXECUTOR
                = new ThreadPoolExecutor(CPU_COUNT + 1, CPU_COUNT * 2 + 1, 2,
                TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
        Initdata.init(this, inithandler);
    }

    private void initView() {
        photo = (RadioButton) findViewById(R.id.photo);
        video = (RadioButton) findViewById(R.id.video);
        collect = (RadioButton) findViewById(R.id.collect);
        mine = (RadioButton) findViewById(R.id.mine);
        photo.setOnClickListener(this);
        video.setOnClickListener(this);
        collect.setOnClickListener(this);
        mine.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View v) {
        selectFG(v.getId(), R.id.frame_root_fragment);
    }

    private void selectFG(int vId, int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("FGID", vId);
        baseFragment = new BaseFragment();
        baseFragment.setArguments(bundle);
        switch (vId) {
            case R.id.photo:
                add(id, baseFragment, "photo");
                break;
            case R.id.video:
                add(id, baseFragment, "video");
                break;
            case R.id.collect:
                add(id, baseFragment, "collect");
                break;
            case R.id.mine:
                add(id, baseFragment, "mine");
                break;
            default:
                add(id, baseFragment, "photo");
        }
    }

    private void add(int id, BaseFragment baseFragment, String tag) {
        Log.d(TAG, "add: " + tag);
        Fragment fragmentByTag = manager.findFragmentByTag(tag);
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragmentByTag != null) baseFragment = (BaseFragment) fragmentByTag;
        if (baseFragment.isAdded()) {
            addOrShowFragment(transaction, id, baseFragment, tag);
        } else {
            Log.d(TAG, "add: " + currentFG + "  " + currentFG.isAdded() + "  " + tag);
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

    private static class InitDataHandler extends BaseHandler {
        private final WeakReference<MainActivity> mainActivityWeakReference;

        private InitDataHandler(MainActivity activity) {
            this.mainActivityWeakReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mainActivityWeakReference.get() == null) {
                return;
            }
            if (msg.what == 200) {
                String initData = msg.getData().getString("initData", "");
                if (initData != null) {
                    Gson gson = new Gson();
                    BaseUrl baseUrl = gson.fromJson(initData, BaseUrl.class);
                    BaseUrl.DataObjBean.UrlInfoBean urlInfo = baseUrl.getDataObj().getUrlInfo();
                    String picUrlPrefix = urlInfo.getPicUrlPrefix();
                    Log.d(TAG, "handleMessage: " + picUrlPrefix);
                }
            }
        }

    }
}
