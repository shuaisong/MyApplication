package com.example.lenovo.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Created by lenovo on 2018/4/26.
 * auther:lenovo
 * Date：2018/4/26
 */

public class CheckNet {
    private static CheckNet checkNet;

    private CheckNet() {
    }

    public static CheckNet getIntance() {
        if (checkNet == null) {
            checkNet = new CheckNet();
        }
        return checkNet;
    }
    /**
     * @param context 上下文
     * @return 是否有网络连接
     */
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mSystemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert mSystemService != null;
            NetworkInfo mNetworkInfo = mSystemService.getActiveNetworkInfo();
            return mNetworkInfo != null && mNetworkInfo.isAvailable();
        }
        return false;
    }

    /**
     * @param context context
     * @return wifi 是否可用
     */
    public boolean isWifiConnected(Context context) {
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
     * 是否使用代理(WiFi状态下的,避免被抓包)
     */
    public static boolean isWifiProxy(Context context) {
        final boolean is_ics_or_later = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
        String proxyAddress;
        int proxyPort;
        if (is_ics_or_later) {
            proxyAddress = System.getProperty("http.proxyHost");
            String portstr = System.getProperty("http.proxyPort");
            proxyPort = Integer.parseInt((portstr != null ? portstr : "-1"));
            System.out.println(proxyAddress + "~");
            System.out.println("port = " + proxyPort);
        } else {
            proxyAddress = android.net.Proxy.getHost(context);
            proxyPort = android.net.Proxy.getPort(context);
            Log.e("address = ", proxyAddress + "~");
            Log.e("port = ", proxyPort + "~");
        }
        return (!TextUtils.isEmpty(proxyAddress)) && (proxyPort != -1);
    }

    /**
     * 是否正在使用VPN
     */
    public static boolean isVpnUsed() {
        try {
            Enumeration<NetworkInterface> niList = NetworkInterface.getNetworkInterfaces();
            if (niList != null) {
                for (NetworkInterface intf : Collections.list(niList)) {
                    if (!intf.isUp() || intf.getInterfaceAddresses().size() == 0) {
                        continue;
                    }
                    Log.d("-----", "isVpnUsed() NetworkInterface Name: " + intf.getName());
                    if ("tun0".equals(intf.getName()) || "ppp0".equals(intf.getName())) {
                        return true; // The VPN is up
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
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
