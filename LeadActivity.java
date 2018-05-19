package com.example.lenovo.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.lenovo.myapplication.base.BaseActivity;
import com.example.lenovo.myapplication.base.BaseHandler;
import com.example.lenovo.myapplication.bean.BaseUrl;
import com.example.lenovo.myapplication.utils.Initdata;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;

/**
 * Created by lenovo on 2018/4/26.
 * auther:lenovo
 * Date：2018/4/26
 */

public class LeadActivity extends BaseActivity {
    BaseHandler inithandler = new InitDataHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        Initdata.init(this, inithandler);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private static class InitDataHandler extends BaseHandler {
        private final WeakReference<LeadActivity> weakReference;

        private InitDataHandler(LeadActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (weakReference.get() == null) {
                return;
            }
            if (msg.what == BaseHandler.NO_NET_CONN) {
                Toast.makeText(App.getInstance(), "网络连接错误", Toast.LENGTH_SHORT).show();
                startActivity();
            }
            if (msg.what == 200) {
                String initData = msg.getData().getString("initData", "");
                {
                    if (initData != null && !initData.equals("")) {
                        Gson gson = new Gson();
                        BaseUrl baseUrl = gson.fromJson(initData, BaseUrl.class);
                        BaseUrl.DataObjBean.UrlInfoBean urlInfo = baseUrl.getDataObj().getUrlInfo();
                        SharedPreferences baseURL = weakReference.get().getSharedPreferences("baseUrl", MODE_PRIVATE);
                        if (baseURL.getBoolean("initUrl", false)) {
                            Logger.d("initUrl:true");
                            startActivity();
                        } else {
                            SharedPreferences.Editor edit = baseURL.edit();
                            edit.putString("backUrlprefix", urlInfo.getBackUrlprefix());
                            edit.putString("labelUrlprefix", urlInfo.getLabelUrlprefix());
                            edit.putString("netCheckUrl", urlInfo.getNetCheckUrl());
                            edit.putString("picSearchUrlprefix", urlInfo.getPicUrlPrefix());
                            edit.putString("picSmallUrlprefix", urlInfo.getPicSmallUrlprefix());
                            edit.putString("picThumbUrlprefix", urlInfo.getPicThumbUrlprefix());
                            edit.putString("picUrlPrefix", urlInfo.getPicUrlPrefix());
                            edit.putString("videoSearchUrlprefix", urlInfo.getVideoSearchUrlprefix());
                            edit.putString("videoUrlPrefix", urlInfo.getVideoUrlPrefix());
                            edit.putString("videoThumbUrlprefix", urlInfo.getPicThumbUrlprefix());
                            edit.putBoolean("initUrl", true).apply();
                            Logger.d(urlInfo);
                            startActivity();
                        }
                    } else {
                        Logger.d(initData);
                        Toast.makeText(weakReference.get(), "服务器数据错误", Toast.LENGTH_SHORT).show();
                        startActivity();
                    }
                }
            }
        }

        private void startActivity() {
            SharedPreferences isFirst = weakReference.get().getSharedPreferences("isfirst", MODE_PRIVATE);

            if (isFirst.getBoolean("isFirst", false)) {
                Intent intent = new Intent(weakReference.get(), WelcomeActivity.class);
                weakReference.get().startActivity(intent);
                weakReference.get().finish();
            } else {
                Intent intent = new Intent(weakReference.get(), MainActivity.class);
                weakReference.get().startActivity(intent);
                weakReference.get().finish();
            }
        }
    }

}
