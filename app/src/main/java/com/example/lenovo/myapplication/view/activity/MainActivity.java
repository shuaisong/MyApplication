package com.example.lenovo.myapplication.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.lenovo.myapplication.Contact.MainContact;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseActivity;
import com.example.lenovo.myapplication.bean.VersionCode;
import com.example.lenovo.myapplication.bean.msg.FindVersion;
import com.example.lenovo.myapplication.bean.msg.InstalAPK;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.component.DaggerActivityComponent;
import com.example.lenovo.myapplication.presenter.MainPresenter;
import com.example.lenovo.myapplication.utils.StatusBarUtil;
import com.example.lenovo.myapplication.utils.Util;
import com.example.lenovo.myapplication.view.fragment.CollectFG;
import com.example.lenovo.myapplication.view.fragment.MineFG;
import com.example.lenovo.myapplication.view.fragment.PhotoFG;
import com.example.lenovo.myapplication.view.fragment.VideoFG;
import com.vise.xsnow.permission.OnPermissionCallback;
import com.vise.xsnow.permission.PermissionManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import cn.jzvd.JZVideoPlayer;


public class MainActivity extends BaseActivity implements View.OnClickListener, MainContact.View {
    @Inject
    MainPresenter presenter;
    private Fragment currentFG;
    private PhotoFG photoFG;
    private VideoFG videoFG;
    private CollectFG collectFG;
    private MineFG mineFG;
    private AlertDialog dialog;

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        selectFG(R.id.photo, R.id.frame_root_fragment);
    }

    @Override
    protected void initViews() {
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, false);
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.colorAccent));

        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        /*if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.colorAccent));
        }*/
        presenter.attachView(this);
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
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onDestroy() {
        /*RefWatcher refWatcher = App.getRefWatcher(this);//1
        refWatcher.watch(this);*/
        presenter.detachView();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void installApk(InstalAPK apk) {
        Util.instanllAPK(this,apk.getPath());
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getVersionInfo(FindVersion findVersion) {
        final VersionCode.DataObjBean bean = findVersion.getBean();
        String mUpdateContent = bean.getUpdateContent();
        int one = mUpdateContent.lastIndexOf("1");
        int two = mUpdateContent.lastIndexOf("2");
        int three = mUpdateContent.lastIndexOf("3");
        String[] contents = new String[3];
        contents[0] = mUpdateContent.substring(one, two).trim();
        contents[1] = mUpdateContent.substring(two, three).trim();
        contents[2] = mUpdateContent.substring(three).trim();
        @SuppressLint("InflateParams") View mView = LayoutInflater.from(this).inflate(R.layout.activity_show_version_dialog, null);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setView(mView);
        dialog = mBuilder.setCancelable(true).show();
        configDialog(dialog);
        Button update = mView.findViewById(R.id.update);
        ImageButton close = mView.findViewById(R.id.dialog_close);
        ListView content = mView.findViewById(R.id.updateContent);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, R.layout.simple_list_item, contents);
        content.setEnabled(false);
        content.setAdapter(mAdapter);
        close.setOnClickListener(this);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionManager.instance().request(MainActivity.this, new OnPermissionCallback() {
                    @Override
                    public void onRequestAllow(String permissionName) {
                        String mUrl = bean.getPackageUrl();
                        presenter.downloadAPK(mUrl);
                        dialog.dismiss();
                    }

                    @Override
                    public void onRequestRefuse(String permissionName) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onRequestNoAsk(String permissionName) {
                        dialog.dismiss();
                    }
                }, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        });
    }

    private void configDialog(Dialog dialog) {
        Display mDisplay = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        mDisplay.getMetrics(outMetrics);
        Window dialogWindow = dialog.getWindow();
        assert dialogWindow != null;
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.width = (int) (outMetrics.widthPixels * 0.7);
        layoutParams.height = (int) (outMetrics.heightPixels * 0.5);
        dialogWindow.setAttributes(layoutParams);
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
            case R.id.dialog_close:
                dialog.dismiss();
                break;
            default:
                if (photoFG == null)
                    photoFG = new PhotoFG();
                add(id, photoFG, "photo");
                break;
        }
    }

    private void add(int id, Fragment fragment, String tag) {
        if (currentFG == fragment) {
            return;
        }
        FragmentManager mManager = getSupportFragmentManager();
        Fragment fragmentByTag = mManager.findFragmentByTag(tag);
        FragmentTransaction transaction = mManager.beginTransaction();
        if (fragmentByTag != null) fragment =  fragmentByTag;
        if (fragment.isAdded()) {
            transaction.hide(currentFG).show(fragment).commit();
        } else {
            if (currentFG != null && currentFG.isAdded()) {
                transaction.hide(currentFG).add(id, fragment, tag).show(fragment).commit();
            } else {
                transaction.add(id, fragment, tag).commit();
            }
        }
        currentFG = fragment;
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        exit();
    }

   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            return false;
        }
        return super.onKeyDown(keyCode, event);
    }*/

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

    @Override
    public void start() {

    }

    @Override
    public void compete() {

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
