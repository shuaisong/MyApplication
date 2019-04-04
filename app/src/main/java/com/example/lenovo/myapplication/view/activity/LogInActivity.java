package com.example.lenovo.myapplication.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.Login;
import com.example.lenovo.myapplication.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInActivity extends Activity implements ViewTreeObserver.OnGlobalLayoutListener {


    @BindView(R.id.btn_login)
    ImageView mBtnLogin;
    @BindView(R.id.root)
    ConstraintLayout mRoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);

        mRoot.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }



    @OnClick(R.id.close)
    public void onViewClicked() {
        mRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        Intent mIntent = new Intent();
        mIntent.putExtra("login",false);
        setResult(102,mIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        onViewClicked();
    }

    /**
     * CallBack method to be invoked when the global layout state or the visibility of views
     * within the view tree changes
     */
    @Override
    public void onGlobalLayout() {
        Rect rect = new Rect();
        mRoot.getWindowVisibleDisplayFrame(rect);
        int displayHeight = rect.bottom - rect.top;
        int height = mRoot.getHeight();
        boolean visible = (double) displayHeight / height < 0.8;

        if (visible) {

            ConstraintLayout.LayoutParams mLayoutParams = (ConstraintLayout.LayoutParams) mBtnLogin.getLayoutParams();
            mLayoutParams.topMargin = (int) LogInActivity.this.getResources().getDimension(R.dimen.dp_20);
            mBtnLogin.setLayoutParams(mLayoutParams);
            LogUtil.d("软键盘显示");
        } else {
            ConstraintLayout.LayoutParams mLayoutParams = (ConstraintLayout.LayoutParams) mBtnLogin.getLayoutParams();
            mLayoutParams.topMargin = (int) LogInActivity.this.getResources().getDimension(R.dimen.dp_80);
            mBtnLogin.setLayoutParams(mLayoutParams);
            LogUtil.d("软键盘隐藏");
        }
    }
}
