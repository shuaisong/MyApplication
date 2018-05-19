package com.example.lenovo.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;

/**
 * Created by lenovo on 2018/4/26.
 * auther:lenovo
 * Date：2018/4/26
 */

public class CheckNet {

    /**
     * @param context 上下文
     * @return 是否有网络连接
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mSystemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert mSystemService != null;
            NetworkInfo mNetworkInfo = mSystemService.getActiveNetworkInfo();
            if (mNetworkInfo != null) return mNetworkInfo.isAvailable();
        }
        return false;
    }

    /**
     * @param context context
     * @return wifi 是否可用
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mSystemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert mSystemService != null;
            NetworkInfo mNetworkInfo = mSystemService.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            return mNetworkInfo != null && mNetworkInfo.isAvailable();
        }
        return false;
    }

    /**
     * @param context context
     * @return MOBILE网络是否可用
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mSystemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert mSystemService != null;
            NetworkInfo mNetworkInfo = mSystemService.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            return mNetworkInfo != null && mNetworkInfo.isAvailable();
        }
        return false;
    }

    /**
    public static final int TYPE_NONE        = -1;


    public static final int TYPE_MOBILE      = 0;

    public static final int TYPE_WIFI        = 1;

    public static final int TYPE_MOBILE_MMS  = 2;

    public static final int TYPE_MOBILE_SUPL = 3;

    public static final int TYPE_MOBILE_DUN  = 4;

    public static final int TYPE_MOBILE_HIPRI = 5;

    public static final int TYPE_WIMAX       = 6;

    public static final int TYPE_BLUETOOTH   = 7;

    public static final int TYPE_DUMMY       = 8;

    public static final int TYPE_ETHERNET    = 9;

    public static final int TYPE_MOBILE_FOTA = 10;

    public static final int TYPE_MOBILE_IMS  = 11;

    public static final int TYPE_MOBILE_CBS  = 12;

    public static final int TYPE_WIFI_P2P    = 13;

    public static final int TYPE_MOBILE_IA = 14;

    public static final int TYPE_MOBILE_EMERGENCY = 15;

    public static final int TYPE_PROXY = 16;

    public static final int TYPE_VPN = 17;

    public static final int MAX_RADIO_TYPE   = TYPE_VPN;

    public static final int MAX_NETWORK_TYPE = TYPE_VPN;
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mSystemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert mSystemService != null;
            NetworkInfo mNetworkInfo = mSystemService.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }
}
