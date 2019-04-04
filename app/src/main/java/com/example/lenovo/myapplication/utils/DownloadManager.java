package com.example.lenovo.myapplication.utils;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.example.lenovo.myapplication.bean.msg.InstalAPK;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

/**
 * Created by lenovo on 2018/8/16.
 * auther:lenovo
 * Date：2018/8/16
 */

public class DownloadManager {

    //    private static String APK_CONTENTTYPE = "application/vnd.android.package-archive";
//    private static String APK_CONTENTTYPE = "application/octet-stream";
    private static String APK_CONTENTTYPE = "application";

    private static String PNG_CONTENTTYPE = "image/png";

    private static String JPG_CONTENTTYPE = "image/jpg";

    private static String fileSuffix = "";
    private static DownloadManager manager;

    private DownloadManager() {
    }

    public static DownloadManager getInstance() {
        if (manager == null) {
            manager = new DownloadManager();
        }
        return manager;
    }

    public void writeFile(final Context context, final ResponseBody body,
                          final NotificationManager notificationManager, final NotificationCompat.Builder builder) {
        if (body != null && body.contentType() != null) {
            netFileType(body);
            final String path = context.getExternalFilesDir(null) + File.separator + Util.getAppName(context) + fileSuffix;
            LogUtil.d("path>>" + path);
            final File mFile = new File(path);
            if (mFile.exists()) {
                InstalAPK mInstalAPK = new InstalAPK();
                mInstalAPK.setPath(path);
               EventBus.getDefault().postSticky(mInstalAPK);
                return;
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    InputStream is = null;
                    FileOutputStream os = null;
                    try {
                        byte[] fileRead = new byte[4096];
                        long fileLength = body.contentLength();
                        long fileDownLength = 0;
                        is = body.byteStream();
                        os = new FileOutputStream(mFile);
                        int mRead = -1;
                        while (true) {
                            mRead = is.read(fileRead);
                            if (mRead == -1) break;
                            os.write(fileRead, 0, mRead);
                            fileDownLength += mRead;
                            NumberFormat mNumberFormat = NumberFormat.getPercentInstance();
                            mNumberFormat.setMaximumFractionDigits(0);
                            String mFormat = mNumberFormat.format(fileDownLength / fileLength);
                            builder.setProgress((int) fileLength, (int) fileDownLength, false);
                            notificationManager.notify(0x3, builder.build());
                        }
                        os.flush();
                        os.close();
                        is.close();
                        builder.setContentInfo("下载完成");
                        notificationManager.notify(0x3, builder.build());
                        if (fileSuffix.equals(".apk")) {
                            InstalAPK mInstalAPK = new InstalAPK();
                            mInstalAPK.setPath(path);
                       EventBus.getDefault().postSticky(mInstalAPK);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        try {
                            if (is != null)
                                is.close();
                            if (os != null)
                                os.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } finally {
                        try {
                            if (is != null)
                                is.close();
                            if (os != null)
                                os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        }
    }


    private void netFileType(ResponseBody body) {

        MediaType mMediaType = body.contentType();
        assert mMediaType != null;
        String type = mMediaType.type();
        LogUtil.d("contentType:>>>>" + type);
        if (APK_CONTENTTYPE.equals(type)) {
            fileSuffix = ".apk";
        } else if (PNG_CONTENTTYPE.equals(type)) {
            fileSuffix = ".png";
        } else if (JPG_CONTENTTYPE.equals(type)) {
            fileSuffix = ".jpg";
        }
    }

}
