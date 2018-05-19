package com.example.lenovo.myapplication.utils;

import android.content.Context;

/**
 * Author       ysh
 * Date         2018/4/11
 * Desc	        ${转换工具类}
 */
public class ConvertUtil {


    /**
     * dp转换为px
     *
     * @param context
     * @param dpValue dp值
     * @return px值
     */
    public static float dp2px(Context context, final float dpValue) {
//        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        float mDensity = context.getResources().getDisplayMetrics().density;
        return dpValue * mDensity + 0.5f;
    }

    /**
     * px转换为dp
     *
     * @param context
     * @param pxValue px值
     * @return dp值
     */
    public static float px2dp(Context context, final float pxValue) {
        float mDensity = context.getResources().getDisplayMetrics().density;
        return pxValue / mDensity + 0.5f;
    }

    /**
     * sp转换为px
     *
     * @param context
     * @param spValue sp值
     * @return px值
     */
    public static float sp2px(Context context, final float spValue) {
        float mScaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return spValue * mScaledDensity + 0.5f;
    }

    /**
     * px转换为sp
     *
     * @param context
     * @param pxValue px值
     * @return sp值
     */
    public static float px2sp(Context context, final float pxValue) {
        float mScaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return pxValue / mScaledDensity + 0.5f;
    }
}
