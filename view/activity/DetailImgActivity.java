package com.example.lenovo.myapplication.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lenovo.myapplication.Adapter.DetailImgAdapter;
import com.example.lenovo.myapplication.Contact.DetailImgContact;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseActivity;
import com.example.lenovo.myapplication.bean.ArticlePicDetail;
import com.example.lenovo.myapplication.bean.DetailImgUrl;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.component.DaggerActivityComponent;
import com.example.lenovo.myapplication.presenter.DetailImgPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class DetailImgActivity extends BaseActivity implements DetailImgContact.View {
    @Inject
    DetailImgPresenter presenter;
    @BindView(R.id.detail_list)
    RecyclerView mDetailList;
    private String[] signs = Contant.getImgDetailSampleList().get("signs");
    private String[] apids = Contant.getImgDetailSampleList().get("apid");
    private List<DetailImgUrl.DataObjBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            //int mApid = mBundle.getInt(Constants.APID, 4244);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        presenter.getPicUrlList(Integer.valueOf(apids[0]), signs[0]);
    }

    @Override
    protected void initViews() {
        presenter.attachView(this);
        LinearLayoutManager mManager = new LinearLayoutManager(this);
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDetailList.setLayoutManager(mManager);
        list = new ArrayList<>();
        DetailImgAdapter mAdapter = new DetailImgAdapter(this, list, false);
        mDetailList.setAdapter(mAdapter);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_detail_img;
    }

    @Override
    public void start() {

    }

    @Override
    public void compete() {

    }

    @Override
    public void showImg(DetailImgUrl.DataObjBean dataObj) {
        list.add(list.size(), dataObj);
        mDetailList.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showArticle(ArticlePicDetail.DataObjBean dataObj) {

    }
}
