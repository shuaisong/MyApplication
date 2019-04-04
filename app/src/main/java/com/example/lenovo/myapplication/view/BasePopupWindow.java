package com.example.lenovo.myapplication.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.lenovo.myapplication.R;

/**
 * Created by lenovo on 2018/8/31.
 * auther:lenovo
 * Date：2018/8/31
 */
public abstract class BasePopupWindow {
    protected Activity context;
    protected PopupWindow mInstance;

    public BasePopupWindow(Activity context, int popupLayoutId) {
        this.context = context;
        View mPopupView = LayoutInflater.from(context).inflate(popupLayoutId, null);
        initView(mPopupView);
        initSize(mPopupView);
        initEvent();
        initWindow();
    }

    protected abstract void initSize(View mPopupView);

    protected abstract void initView(View popupView);

    public PopupWindow getInstance() {
        return mInstance;
    }

    private void initWindow() {
        mInstance.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mInstance.setOutsideTouchable(true);
        mInstance.setTouchable(true);
        mInstance.setAnimationStyle(R.style.mypopwindow_anim_style);
        mInstance.setFocusable(true);
        darkenBackground(0.5f);

    }

    private void darkenBackground(float f) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = f;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    private void initEvent() {
        mInstance.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground(1f);
            }
        });
    }
}
