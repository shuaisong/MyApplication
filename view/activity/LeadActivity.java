package com.example.lenovo.myapplication.view.activity;

import android.content.Intent;
import android.view.KeyEvent;

import com.example.lenovo.myapplication.Contact.LeadContact;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseActivity;
import com.example.lenovo.myapplication.bean.BaseUrl;
import com.example.lenovo.myapplication.bean.FeedBack;
import com.example.lenovo.myapplication.bean.RecommendLabel;
import com.example.lenovo.myapplication.bean.SearchType;
import com.example.lenovo.myapplication.bean.VersionCode;
import com.example.lenovo.myapplication.bean.msg.FindVersion;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.component.DaggerActivityComponent;
import com.example.lenovo.myapplication.presenter.LeadPresenter;
import com.example.lenovo.myapplication.utils.PreferenceManager;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/4/26.
 * auther:lenovo
 * Date：2018/4/26
 */

public class LeadActivity extends BaseActivity implements LeadContact.View {
    @Inject
    LeadPresenter presenter;

    @Override
    protected void initData() {
        presenter.initData();
        presenter.checkVersion();
        presenter.feedBack();
        presenter.getSearchKey();
        presenter.getRecommend();
    }

    @Override
    protected void initViews() {
        presenter.attachView(this);
    }

    @Override
    protected void justShowUI() {
        startActivity();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_lead;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode != KeyEvent.KEYCODE_BACK && super.onKeyDown(keyCode, event);
    }

    @Override
    public void start() {

    }

    @Override
    public void compete() {

    }

    @Override
    public void saveData(BaseUrl.DataObjBean.UrlInfoBean bean) {
        PreferenceManager.getInstance().setUrl(bean);
        startActivity();
    }

    @Override
    public void showVersion(VersionCode.DataObjBean bean) {
        if (bean.getUpdateType() == 1)
            EventBus.getDefault().post(new FindVersion(bean));
    }

    @Override
    public void showFeedBack(FeedBack.DataObjBean feedBack) {

    }

    @Override
    public void saveSearchKey(SearchType.DataObjBean bean, String type) {
        PreferenceManager.getInstance().saveSearchKey(bean, type);
    }

    @Override
    public void saveRecommend(RecommendLabel.DataObjBean bean) {
        PreferenceManager.getInstance().saveRecommend(bean);
    }

    private void startActivity() {
        boolean mIsFirst = PreferenceManager.getInstance().getIsFirst();
        Intent intent;
        if (mIsFirst) {
            intent = new Intent(this, WelcomeActivity.class);
        } else {
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }
}