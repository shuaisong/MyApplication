package com.example.lenovo.myapplication.view;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.example.lenovo.myapplication.R;

/**
 * Created by lenovo on 2018/8/25.
 * auther:lenovo
 * Date：2018/8/25
 */
public class PopupWindowConfig extends BasePopupWindow{
    public PopupWindowConfig(Activity context, int popupLayoutId) {
        super(context, popupLayoutId);
    }

    @Override
    protected void initSize(View mPopupView) {
        DisplayMetrics mMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
        mInstance = new PopupWindow(mPopupView, (int) (mMetrics.widthPixels * 0.9f), mMetrics.heightPixels);
    }

    protected void initView(View popupView) {
      ImageView close = popupView.findViewById(R.id.popup_close);
        final EditText verify = popupView.findViewById(R.id.verify);
        final ConstraintLayout parent = popupView.findViewById(R.id.parent);
        popupView.findViewById(R.id.phone).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              verify.setVisibility(View.VISIBLE);
              ConstraintSet set = new ConstraintSet();
              set.clone(parent);
              set.setMargin(R.id.other_way, ConstraintSet.TOP,(int)context.getResources().getDimension(R.dimen.dp_200));
              set.applyTo(parent);
          }
      });
      close.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              mInstance.dismiss();
          }
      });
    }

}
