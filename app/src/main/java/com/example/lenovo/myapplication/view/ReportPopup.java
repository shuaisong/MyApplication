package com.example.lenovo.myapplication.view;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.utils.ToastUtil;

/**
 * Created by lenovo on 2018/8/31.
 * auther:lenovo
 * Date：2018/8/31
 */
public class ReportPopup extends BasePopupWindow implements View.OnClickListener {
    public ReportPopup(Activity context, int popupLayoutId) {
        super(context, popupLayoutId);
    }

    @Override
    protected void initSize(View mPopupView) {
        DisplayMetrics mMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
        mInstance = new PopupWindow(mPopupView, mMetrics.widthPixels, mMetrics.heightPixels / 6);
    }

    @Override
    protected void initView(View popupView) {
        Button report = popupView.findViewById(R.id.report);
        Button cancel = popupView.findViewById(R.id.cancel);
        report.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.report:
                ToastUtil.show(context.getString(R.string.report_success));
            case R.id.cancel:
                mInstance.dismiss();
                break;
        }
    }
}
