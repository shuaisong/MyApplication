package com.example.lenovo.myapplication.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;

import com.example.lenovo.myapplication.App;
import com.example.lenovo.myapplication.Contant;

import java.io.File;
import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;

/**
 * Time: 2016/9/1 11:57
 */
public class Util {
    /**
     * StaggeredGridLayoutManager时，查找position最大的列
     *
     * @param lastVisiblePositions
     * @return
     */
    public static int findMax(int[] lastVisiblePositions) {
        int max = lastVisiblePositions[0];
        for (int value : lastVisiblePositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static View inflate(Context context, int layoutId) {
        if (layoutId <= 0) {
            return null;
        }
        return LayoutInflater.from(context).inflate(layoutId, null);
    }

    public static synchronized String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static synchronized void instanllAPK(Context context, String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //版本在7.0以上是不能直接通过uri访问的
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            File file = (new File(path));
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(App.getInstance(), "com.example.lenovo.myapplication", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            context.startActivity(intent);
        } else {
            intent.setDataAndType(Uri.fromFile(new File(path)),
//            intent.setDataAndType(Uri.parse("file://"+path),
                    "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
    }

    public static String getSign(TreeMap<String,String> map) {
        TreeMap<String,String> localTreeMap = new TreeMap<>();
        localTreeMap.put("token", Contant.tokenValue);
        localTreeMap.put("applicationId", "1");
        localTreeMap.put("api_version", "v20");
        localTreeMap.put("did", "865166020148113,460005611084481,fee69b33c464d6e2");
        localTreeMap.put("appkey", "06E0C9C16A169AEC");
        localTreeMap.put("channel", "GW_3");
        localTreeMap.put("client_version", "1.6.3");
        localTreeMap.putAll(map);
        StringBuilder localStringBuffer = new StringBuilder();
        for (Object localObject : localTreeMap.entrySet()) {
            String str = (String) ((Map.Entry) localObject).getKey();
            localObject = ((Map.Entry) localObject).getValue();
            if ((localObject != null) && (!"".equals(localObject)) && (!"sign".equals(str)) && (!"key".equals(str))) {
                localStringBuffer.append(str).append("=").append(localObject).append("&");
            }
        }
        localStringBuffer.append("EF906E3FEE70F22A");
        return getSignMD5(localStringBuffer.toString());
    }

    private static String getSignMD5(String paramString1) {
        try {
            MessageDigest localMessageDigest;
            localMessageDigest = MessageDigest.getInstance("MD5");
            return a(localMessageDigest.digest(paramString1.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String a(byte[] paramArrayOfByte) {
        char[] a = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};
        int i = 0;
        if (paramArrayOfByte == null) { return null; }
        int k;
        do {
            k = paramArrayOfByte.length;
        } while (k <= 0);
        char[] arrayOfChar = new char[k << 1];
        int j = 0;
        while (i < k) {
            int m = j + 1;
            arrayOfChar[j] = a[(paramArrayOfByte[i] >>> 4 & 0xF)];
            j = m + 1;
            arrayOfChar[m] = a[(paramArrayOfByte[i] & 0xF)];
            i += 1;
        }
        return new String(arrayOfChar);
    }
}
