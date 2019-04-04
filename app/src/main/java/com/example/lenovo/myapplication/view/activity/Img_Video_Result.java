package com.example.lenovo.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.myapplication.Adapter.ImgResultAdapter;
import com.example.lenovo.myapplication.Adapter.VideoResultAdapter;
import com.example.lenovo.myapplication.Adapter.ViewHolder;
import com.example.lenovo.myapplication.Contact.ImgVideoContact;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseActivity;
import com.example.lenovo.myapplication.bean.ArticleListBean;
import com.example.lenovo.myapplication.bean.LabelContent21;
import com.example.lenovo.myapplication.bean.RecommendLabel;
import com.example.lenovo.myapplication.bean.SpaceItemDecoration;
import com.example.lenovo.myapplication.bean.VideoListBean;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.component.DaggerActivityComponent;
import com.example.lenovo.myapplication.interfaces.OnItemClickListener;
import com.example.lenovo.myapplication.interfaces.OnLoadMoreListener;
import com.example.lenovo.myapplication.presenter.ImgVideoPresenter;
import com.example.lenovo.myapplication.utils.OSUtils;
import com.example.lenovo.myapplication.utils.PreferenceManager;
import com.example.lenovo.myapplication.utils.StatusBarUtil;
import com.example.lenovo.myapplication.utils.Util;
import com.example.lenovo.myapplication.view.BlurTransformation;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Img_Video_Result extends BaseActivity implements ImgVideoContact.View, SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {
    @Inject
    ImgVideoPresenter presenter;
    @BindView(R.id.result_recycler)
    RecyclerView mResultRecycler;
    @BindView(R.id.swip)
    SwipeRefreshLayout mSwip;
    @BindView(R.id.fruit_image_view)
    ImageView mFruitImageView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    private int type;
    private List<ArticleListBean> imgListBeans;
    private ImgResultAdapter imgResultAdapter;
    private RecommendLabel.DataObjBean.LabelListBean listBean;
    private List<VideoListBean> videoListBeans;
    private VideoResultAdapter videoResultAdapter;
    TreeMap<String, String> map = new TreeMap<>();

    @Override
    protected void initData() {
        Intent mIntent = getIntent();
        listBean = (RecommendLabel.DataObjBean.LabelListBean) mIntent.getSerializableExtra("ListBean");
        mToolbar.setTitle(listBean.getLabel_name());
        type = listBean.getLabel_type();
        mToolbar.setSubtitle(listBean.getContent_num() + type == 1 ? "套图" : "个视频");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        Picasso.get().load(PreferenceManager.getInstance().getLabelUrlprefix() + listBean.getBack_url()).into(mFruitImageView);
        Glide.with(this).load(PreferenceManager.getInstance().getLabelUrlprefix() + listBean.getBack_url())
                .placeholder(R.mipmap.pic_classify_default)

//                .transform(new BlurTransformation(this,23,1))
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(23, 1))).into(mFruitImageView);
        switch (type) {
            case 1:
                GridLayoutManager mManager = new GridLayoutManager(this, 2);
                mResultRecycler.setLayoutManager(mManager);
                imgListBeans = new ArrayList<>();
                imgResultAdapter = new ImgResultAdapter(this, imgListBeans, true);
                imgResultAdapter.setObject(this);
                mResultRecycler.addItemDecoration(new SpaceItemDecoration(10));
                imgResultAdapter.setLoadFailedView(R.layout.loadfail);
                imgResultAdapter.setLoadEndView(R.layout.sample_common_list_footer_end);
                imgResultAdapter.setLoadingView(R.layout.sample_common_list_footer_loading);
                imgResultAdapter.setOnLoadMoreListener(this);
                mResultRecycler.setAdapter(imgResultAdapter);
                imgResultAdapter.setStatus(Contant.fresh);
                imgResultAdapter.setOnItemClickListener(new OnItemClickListener<ArticleListBean>() {
                    @Override
                    public void onItemClick(ViewHolder viewHolder, ArticleListBean data, int position) {
                        Intent mIntent = new Intent(Img_Video_Result.this, DetailImgActivity.class);
                        mIntent.putExtra("id", imgResultAdapter.getItem(position).getApid());
                        startActivity(mIntent);
                    }
                });
                setMap(String.valueOf(listBean.getLabel_type()), "-1", listBean.getLabel_name());
                presenter.LabelContent21(type, -1, listBean.getLabel_name(), Util.getSign(map));
                break;
            case 2:
                mManager = new GridLayoutManager(this, 1);
                mResultRecycler.setLayoutManager(mManager);
                videoListBeans = new ArrayList<>();
                videoResultAdapter = new VideoResultAdapter(this, videoListBeans, true);
                mResultRecycler.addItemDecoration(new SpaceItemDecoration(10));
                videoResultAdapter.setObject(this);
                videoResultAdapter.setLoadFailedView(R.layout.loadfail);
                videoResultAdapter.setLoadEndView(R.layout.sample_common_list_footer_end);
                videoResultAdapter.setLoadingView(R.layout.sample_common_list_footer_loading);
                videoResultAdapter.setOnLoadMoreListener(this);
                videoResultAdapter.setOnItemClickListener(new OnItemClickListener<VideoListBean>() {
                    @Override
                    public void onItemClick(ViewHolder viewHolder, VideoListBean data, int position) {
                        Intent mIntent = new Intent(Img_Video_Result.this, VideoActivity.class);
                        mIntent.putExtra("avid", videoListBeans.get(position).getAvid());
                        startActivity(mIntent);
                    }
                });
                mResultRecycler.setAdapter(videoResultAdapter);
                videoResultAdapter.setStatus(Contant.fresh);
                setMap(String.valueOf(listBean.getLabel_type()), "-1", listBean.getLabel_name());
                presenter.LabelContent21(type, -1, listBean.getLabel_name(), Util.getSign(map));
                break;
        }
    }

   /* @Override
    protected void modifyStatus() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }*/

    @Override
    protected void initViews() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, false);
        if (OSUtils.isEmui() && OSUtils.hasNotchInScreen(this.getApplicationContext()))
            StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.colorAccent));

        presenter.attachView(this);
        mSwip.setOnRefreshListener(this);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_img_video_result;
    }

    @Override
    public void start() {

    }

    @Override
    public void compete() {

    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    private void setMap(String contentType, String lastId, String tag) {
        map.clear();
        map.put("contentType", contentType);
        map.put("lastId", lastId);
        map.put("pageSize", "12");
        map.put("tag", tag);
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        if (type == 1) {
            setMap(String.valueOf(listBean.getLabel_type()), "-1", listBean.getLabel_name());
            presenter.LabelContent21(type, -1, listBean.getLabel_name(), Util.getSign(map));
        } else {
            setMap(String.valueOf(listBean.getLabel_type()), "-1", listBean.getLabel_name());
            presenter.LabelContent21(type, -1, listBean.getLabel_name(), Util.getSign(map));
        }
    }

    @Override
    public void showImg(LabelContent21.DataObjBean dataObjBean) {
        if (dataObjBean != null) {
            if (imgResultAdapter.getStatus() == Contant.fresh) {
                mSwip.setRefreshing(false);
                if (!imgListBeans.containsAll(dataObjBean.getArticleList())) {
                    imgListBeans.clear();
                    imgListBeans.addAll(dataObjBean.getArticleList());
                }
            } else {
                imgListBeans.addAll(dataObjBean.getArticleList());
                if (dataObjBean.getArticleList().size() < 12) {
                    imgResultAdapter.setmOpenLoadMore(false);
                }
            }
            lastId = imgListBeans.get(imgListBeans.size() - 1).getApid();
        }
        imgResultAdapter.setStatus(Contant.normal);
        imgResultAdapter.notifyDataSetChanged();
        if (mSwip.isRefreshing()) {
            mSwip.setRefreshing(false);
        }
    }

    @Override
    public void showVieo(LabelContent21.DataObjBean dataObjBean) {
        if (dataObjBean != null) {
            if (videoResultAdapter.getStatus() == Contant.fresh) {
                mSwip.setRefreshing(false);
                if (!videoListBeans.containsAll(dataObjBean.getVideoList())) {
                    videoListBeans.clear();
                    videoListBeans.addAll(dataObjBean.getVideoList());
                }
            } else {
                videoListBeans.addAll(dataObjBean.getVideoList());
                if (dataObjBean.getVideoList().size() < 12) {
                    videoResultAdapter.setmOpenLoadMore(false);
                }
            }
            lastId = videoListBeans.get(videoListBeans.size() - 1).getAvid();
        }
        videoResultAdapter.setStatus(Contant.normal);
        videoResultAdapter.notifyDataSetChanged();
        if (mSwip.isRefreshing()) {
            mSwip.setRefreshing(false);
        }
    }

    private int lastId = -1;

    /**
     * 加载更多的回调方法
     *
     * @param isReload 是否是重新加载，只有加载失败后，点击重新加载时为true
     */
    @Override
    public void onLoadMore(boolean isReload) {
        if (type == 1) {
            imgResultAdapter.setStatus(Contant.load);
        } else {
            videoResultAdapter.setStatus(Contant.load);
        }
        setMap(String.valueOf(listBean.getLabel_type()), String.valueOf(lastId), listBean.getLabel_name());
        presenter.LabelContent21(type, lastId, listBean.getLabel_name(), Util.getSign(map));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
