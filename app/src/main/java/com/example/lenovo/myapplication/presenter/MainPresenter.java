package com.example.lenovo.myapplication.presenter;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.lenovo.myapplication.App;
import com.example.lenovo.myapplication.BroadcastReceiver.NotificationReceiver;
import com.example.lenovo.myapplication.Contact.MainContact;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.utils.LogUtil;
import com.example.lenovo.myapplication.utils.ToastUtil;
import com.example.lenovo.myapplication.utils.Util;
import com.example.lenovo.myapplication.view.activity.MainActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.db.DownloadManager;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadListener;
import com.lzy.okserver.download.DownloadTask;
import com.lzy.okserver.task.XExecutor;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public class MainPresenter extends RxPresenter<MainContact.View> implements MainContact.Presenter<MainContact.View>, XExecutor.OnAllTaskEndListener {
    @Inject
    MainPresenter() {
    }
private final static String TAG = "APK";
    @Override
    public void downloadAPK(String url) {
        int mI = url.lastIndexOf("/" );
        final String fileName = url.substring(mI+1);
        final String baseurl = url.substring(0,mI+1);
        OkDownload mOkDownload = OkDownload.getInstance();
//        File mFile = App.getInstance().getCacheDir();
        File mFile = new File(Environment.getExternalStorageDirectory() + File.separator+"MyMM131"+File.separator);
        mOkDownload.setFolder(mFile.getPath());//下载目录
        mOkDownload.getThreadPool().setCorePoolSize(3);//线程
        mOkDownload.addOnAllTaskEndListener(this);
        GetRequest<File> mRequest = OkGo.<File>get(baseurl + fileName);
        final DownloadTask mTask = OkDownload.request("apk", mRequest);
        mTask.fileName(fileName).save().register(new DownloadListener("apk") {
            private Bitmap bitmap;
            private NotificationCompat.Builder builder;
            private NotificationManager notificationManager;
            @Override
            public void onStart(Progress progress) {
                Log.d(TAG, "onStart: "+progress.filePath+progress.folder+progress.fileName);
                Intent intentClick = new Intent((MainActivity)view, NotificationReceiver.class);
                intentClick.setAction("notification_clicked");
                intentClick.putExtra("type", -1);
                intentClick.putExtra("filePath",progress.filePath);
                PendingIntent pendingIntentClick = PendingIntent.getBroadcast((MainActivity)view, 0, intentClick, PendingIntent.FLAG_ONE_SHOT);
                Intent deleteIntent = new Intent((MainActivity) view, NotificationReceiver.class);
                deleteIntent.setAction("notification_cancelled");
                deleteIntent.putExtra("type",-1);
                PendingIntent mPendingIntentDelete = PendingIntent.getBroadcast((MainActivity) view, 0, deleteIntent, PendingIntent.FLAG_ONE_SHOT);
                notificationManager = (NotificationManager) App.getInstance().getSystemService(Activity.NOTIFICATION_SERVICE);
                    builder = new NotificationCompat.Builder(App.getInstance(), "0x101");
                    bitmap = BitmapFactory.decodeResource(((MainActivity) view).getResources(), R.mipmap.icon_app);
                    builder.setAutoCancel(false).
                            setSmallIcon(R.mipmap.icon_app).setLargeIcon(bitmap).setContentTitle("开始下载").setContentInfo("0%");
                    builder.setContentIntent(pendingIntentClick);
                    builder.setDeleteIntent(mPendingIntentDelete);
                    notificationManager.notify(1, builder.build());
            }
            @Override
            public void onProgress(Progress progress) {
                Log.d(TAG, "onProgress: " + progress.currentSize);
                    builder.setProgress((int) progress.totalSize, (int) progress.currentSize, false);
                    builder.setContentTitle("正在下载").setContentInfo("" + (float) progress.currentSize * 100 / progress.totalSize);
                    notificationManager.notify(1, builder.build());
            }
            @Override
            public void onError(Progress progress) {
                Log.d(TAG, "onError: "+progress.exception);
            }

            @Override
            public void onFinish(File file, Progress progress) {
                Log.d(TAG, "onFinish: ");
                boolean mB = file.setExecutable(true, false);
                builder.setProgress((int) progress.totalSize, (int) progress.currentSize, false);
                builder.setContentTitle("下载完成").setContentInfo("100%");
                notificationManager.notify(1,builder.build());
                Util.instanllAPK((MainActivity)view,progress.filePath);
            }

            @Override
            public void onRemove(Progress progress) {
                Log.d(TAG, "onRemove: ");
            }
        });
        mTask.start();
//        ViseHttp.DOWNLOAD(fileName).baseUrl(baseurl)
//        https://download-sdk.oss-cn-beijing.aliyuncs.com/downloads/easemob-sdk-3.5.0.zip
      /*  ViseHttp.DOWNLOAD(fileName)
                .baseUrl(baseurl)
//                .suffixUrl(fileName)
              //  .setDirName(Objects.requireNonNull(App.getInstance().getExternalFilesDir(null)).getAbsolutePath())
                .setFileName(fileName).request(new ACallback<DownProgress>() {

            private Bitmap bitmap;
            private NotificationCompat.Builder builder;
            private NotificationManager notificationManager;

            @Override
            public void onSuccess(DownProgress downProgress) {
                if (downProgress == null) {
                    return;
                }
                *//*if (notificationManager==null)
                notificationManager = (NotificationManager) App.getInstance().getSystemService(Activity.NOTIFICATION_SERVICE);
                if (builder==null)
                builder = new NotificationCompat.Builder(App.getInstance(),"0x101");
                if (bitmap==null)bitmap = BitmapFactory.decodeResource(((MainActivity) view).getResources(), R.mipmap.icon_app);
                builder.setAutoCancel(false).
                            setSmallIcon(R.mipmap.icon_app).setLargeIcon(bitmap).setContentTitle("正在下载").setContentInfo("下载中。。");
                builder.setProgress((int) downProgress.getTotalSize(), (int) downProgress.getDownloadSize(), false);

                if (notificationManager != null) {
                    notificationManager.notify(0x3, builder.build());
                }*//*
                LogUtil.i("down progress currentLength:" + downProgress.getDownloadSize() + ",totalLength:" + downProgress.getTotalSize());
                LogUtil.i((int) (downProgress.getDownloadSize() * 100 / downProgress.getTotalSize()));
                LogUtil.i(downProgress.getPercent());
                if (downProgress.isDownComplete()) {
                  //  builder.setContentText("下载完成").setContentInfo("100%");
                    LogUtil.i(downProgress.isDownComplete()+"");                }
            }

            @Override
            public void onFail(int errCode, String errMsg) {
                LogUtil.i(errCode +","+errMsg);
            }

        });*/
      /*  RetrofitUtil.getInstance().toDownSubscribe(RetrofitUtil.createAPIService().downloadAPK(url),
                ActivityLifeCycleEvent.DESTROY, ((MainActivity) view).lifecycleSubject,
                new JsonObserver<ResponseBody>() {

                    private NotificationManager notificationManager;
                    private NotificationCompat.Builder builder;

                    @Override
                    protected void onBefore(Disposable disposable) {
                        super.onBefore(disposable);
                        notificationManager = (NotificationManager) App.getInstance().getSystemService(Activity.NOTIFICATION_SERVICE);
                        builder = new NotificationCompat.Builder(App.getInstance(), "0x11");
                        Bitmap mBitmap = BitmapFactory.decodeResource(((MainActivity) view).getResources(), R.mipmap.icon_app);
                        builder.setAutoCancel(false).
                                setSmallIcon(R.mipmap.icon_app).setLargeIcon(mBitmap).setContentTitle("正在下载").setContentInfo("下载中。。");
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }

                    @Override
                    public void onResponse(ResponseBody responseBody) {
                        DownloadManager.getInstance().writeFile(App.getInstance(), responseBody, notificationManager, builder);
                    }
                });*/
    }

    @Override
    public void onAllTaskEnd() {
        ToastUtil.show("下载完成");
    }
}
