package com.example.lenovo.myapplication.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowVersionDialog extends AppCompatActivity {

    @BindView(R.id.dialog_close)
    ListView mUpdateContent;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_version_dialog);
        // TODO: 2018/8/12  设置进出动画
        setFinishOnTouchOutside(true);
        ButterKnife.bind(this);
        String[] mContent = getIntent().getStringArrayExtra(Contant.content);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, R.layout.simple_list_item, mContent);
        mUpdateContent.setEnabled(false);
        mUpdateContent.setAdapter(mAdapter);
        Display mDisplay = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        mDisplay.getMetrics(outMetrics);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = (int) (outMetrics.widthPixels * 0.7);
        layoutParams.height = (int) (outMetrics.heightPixels * 0.5);
        getWindow().setAttributes(layoutParams);
    }

    @OnClick({R.id.dialog_close, R.id.update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialog_close:
                finish();
                break;
            case R.id.update:
                finish();
                break;
        }
    }
}
