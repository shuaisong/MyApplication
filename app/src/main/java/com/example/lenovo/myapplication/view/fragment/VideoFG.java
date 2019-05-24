package com.example.lenovo.myapplication.view.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.myapplication.Adapter.VideoAdapter;
import com.example.lenovo.myapplication.Adapter.ViewHolder;
import com.example.lenovo.myapplication.Contact.VideoFGContact;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseFragment;
import com.example.lenovo.myapplication.bean.NewVideo;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.component.DaggerFragmentComponent;
import com.example.lenovo.myapplication.interfaces.OnItemChildClickListener;
import com.example.lenovo.myapplication.interfaces.OnLoadMoreListener;
import com.example.lenovo.myapplication.interfaces.RecyclerViewOnScrollListener;
import com.example.lenovo.myapplication.presenter.VideoFGPresenter;
import com.example.lenovo.myapplication.utils.LogUtil;
import com.example.lenovo.myapplication.utils.Util;
import com.example.lenovo.myapplication.view.ReportPopup;
import com.example.lenovo.myapplication.view.activity.SearchActivity;
import com.example.lenovo.myapplication.view.activity.VideoActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by lenovo on 2018/4/19.
 * auther:lenovo
 * Date：2018/4/19
 */

public class VideoFG extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, VideoFGContact.View, OnBannerListener, OnLoadMoreListener {
    @Inject
    VideoFGPresenter presenter;
    Banner banner;
    @BindView(R.id.video_swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    private List<NewVideo.DataObjBean.NewVideoListBean> videoList;
    private VideoAdapter videoAdapter;
    private int lastIndex;


    @Override
    protected void setupFragComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fg_video;
    }


    @Override
    protected void initView() {
        presenter.attachView(this);
        swipeRefreshLayout=rootView.findViewById(R.id.video_swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        final LinearLayoutManager mManager = new LinearLayoutManager(getContext());
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        final RecyclerView video_recycler = rootView.findViewById(R.id.video_recycler);
        video_recycler.addOnScrollListener(new RecyclerViewOnScrollListener(getContext()));
        video_recycler.setLayoutManager(mManager);
        videoList = new ArrayList<>();
        videoAdapter = new VideoAdapter(getContext(), videoList, true,true);
        View headerView =   LayoutInflater.from(getActivity()).inflate(R.layout.video_header,video_recycler,false);
        videoAdapter.setHeadView(headerView);
        banner = headerView.findViewById(R.id.banner);
        videoAdapter.setLoadFailedView(R.layout.loadfail);
        videoAdapter.setLoadEndView(R.layout.sample_common_list_footer_end);
        videoAdapter.setLoadingView(R.layout.sample_common_list_footer_loading);
        videoAdapter.setOnLoadMoreListener(this);
        video_recycler.setAdapter(videoAdapter);
        videoAdapter.setOnItemChildClickListener(R.id.report, new OnItemChildClickListener<NewVideo.DataObjBean.NewVideoListBean>() {
            @Override
            public void onItemChildClick(ViewHolder viewHolder, NewVideo.DataObjBean.NewVideoListBean data, int position) {
                new ReportPopup(getActivity(),R.layout.report_popup).getInstance().showAtLocation(video_recycler, Gravity.BOTTOM,0,0);
            }
        });
        videoAdapter.setOnItemChildClickListener(R.id.img_commend, new OnItemChildClickListener<NewVideo.DataObjBean.NewVideoListBean>() {
            @Override
            public void onItemChildClick(ViewHolder viewHolder, NewVideo.DataObjBean.NewVideoListBean data, int position) {
                Intent intent = new Intent(getActivity(), VideoActivity.class);
                intent.putExtra("avid",videoList.get(position).getAvid());
                startActivity(intent);
            }
        });

        video_recycler.addOnScrollListener(new RecyclerViewOnScrollListener(getActivity()){

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mManager.findFirstVisibleItemPosition();
            }
        });
        final ImageView search = rootView.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        JZVideoPlayerStandard.releaseAllVideos();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtil.d(hidden+"");
        if (hidden){
            JZVideoPlayerStandard.goOnPlayOnPause();
        }
    }

    @Override
    protected void onFragmentFirstVisible() {
        LogUtil.d("onFragmentFirstVisible:video");
        swipeRefreshLayout.measure(0, 0);
        swipeRefreshLayout.setRefreshing(true);
        videoAdapter.setStatus(Contant.fresh);
        presenter.getVideos(Contant.videosign, "-1");
        presenter.gerAD();
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        videoAdapter.setStatus(Contant.fresh);
        presenter.getVideos(Contant.videosign, "-1");
    }

    @Override
    public void showVideos(NewVideo.DataObjBean dataObj) {
        lastIndex = dataObj.getLastIndex();
        List<NewVideo.DataObjBean.NewVideoListBean> mNewVideoList = dataObj.getNewVideoList();
        Collections.reverse(mNewVideoList);
        if (videoAdapter.getStatus()==Contant.load){
            videoList.addAll(mNewVideoList);
            videoAdapter.notifyItemInserted(videoList.size() - mNewVideoList.size());
        }
        if (videoAdapter.getStatus()==Contant.fresh){
            if (!videoList.containsAll(mNewVideoList)){
                videoList.clear();
                videoList.addAll(mNewVideoList);
            }
            swipeRefreshLayout.setRefreshing(false);
            videoAdapter.notifyDataSetChanged();
        }
        videoAdapter.setStatus(Contant.normal);
    }

    @Override
    public void showBanner(Map<String,List<String>> map) {
        videoAdapter.setBanner(banner,map.get("title"),map.get("imgUrl"));
        videoAdapter.notifyDataSetChanged();
    }

    @Override
    public void start() {

    }

    @Override
    public void compete() {

    }

    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    public void onLoadMore(boolean isReload) {
        TreeMap<String, String> mMap = new TreeMap<>();
        mMap.put("lastIndex",String.valueOf(lastIndex));
        videoAdapter.setStatus(Contant.load);
        presenter.getVideos(Util.getSign(mMap), String.valueOf(lastIndex));
    }
}
