package com.example.lenovo.myapplication.view.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myapplication.Adapter.ImgAdapter;
import com.example.lenovo.myapplication.Adapter.ViewHolder;
import com.example.lenovo.myapplication.App;
import com.example.lenovo.myapplication.Contact.ImgFGContact;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseFragment;
import com.example.lenovo.myapplication.bean.Constants;
import com.example.lenovo.myapplication.bean.HotPhoto;
import com.example.lenovo.myapplication.bean.NewPhoto;
import com.example.lenovo.myapplication.bean.PicListBean;
import com.example.lenovo.myapplication.interfaces.RecyclerViewOnScrollListener;
import com.example.lenovo.myapplication.bean.SpaceItemDecoration;
import com.example.lenovo.myapplication.bean.msg.ChangeRow;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.component.DaggerFragmentComponent;
import com.example.lenovo.myapplication.interfaces.OnItemChildClickListener;
import com.example.lenovo.myapplication.interfaces.OnLoadMoreListener;
import com.example.lenovo.myapplication.presenter.ImgPresenter;
import com.example.lenovo.myapplication.utils.ConvertUtil;
import com.example.lenovo.myapplication.utils.PreferenceManager;
import com.example.lenovo.myapplication.view.activity.DetailImgActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public class ImgFG extends BaseFragment implements ImgFGContact.View, OnItemChildClickListener<PicListBean>, OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @Inject
    ImgPresenter imgPresenter;
    private int type;
    private List<PicListBean> picListBeans;
    private ImgAdapter imgAdapter;
    private RecyclerView list;
    private SwipeRefreshLayout swip;
    private int lastIndex = -1;

    public static ImgFG getInstance(int type) {
        ImgFG mImgFG = new ImgFG();
        Bundle mBundle = new Bundle();
        mBundle.putInt("type", type);
        mImgFG.setArguments(mBundle);
        return mImgFG;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type");
        if (type == Contant.HOT)
            simpleMap = Contant.getHotSampleList();
        else simpleMap = Contant.getNewSampleList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.imgfg, container, false);
        setupFragComponent(App.getInstance().getAppComponent());
        if (type == Contant.HOT)
            setUserVisibleHint(true);
        return rootView;
    }

    private void setupFragComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder().appComponent(appComponent).build().inject(this);
    }

    protected void initData() {
        swip.measure(0, 0);
        swip.setRefreshing(true);
        if (type == Contant.HOT)
            imgPresenter.refreshHot(Contant.imgsign, lastIndex);
        else
            imgPresenter.refreshNew(Contant.imgsign, lastIndex);
    }

    protected void initView() {
        imgPresenter.attachView(this);
        swip = rootView.findViewById(R.id.swip);
        Resources mResources = getActivity().getResources();
        swip.setColorSchemeColors(mResources.getColor(R.color.colorPrimary), mResources.getColor(R.color.colorAccent), mResources.getColor(R.color.black_overlay));
        swip.setOnRefreshListener(this);
        list = rootView.findViewById(R.id.list);
        picListBeans = new ArrayList<>();
        imgAdapter = new ImgAdapter(getActivity(), picListBeans, true, R.layout.photo_list_item1);
        list.setLayoutManager(getLayoutManager());
        imgAdapter.setOnItemChildClickListener(R.id.photo_list_item_image, this);
        imgAdapter.setLoadFailedView(R.layout.loadfail);
        imgAdapter.setLoadEndView(R.layout.loadend);
        imgAdapter.setLoadingView(R.layout.loading);
        imgAdapter.setOnLoadMoreListener(this);
        View emptyView = LayoutInflater.from(getContext()).inflate(R.layout.loadfail, null);
        imgAdapter.setEmptyView(emptyView);
        float mDp2px = ConvertUtil.dp2px(getActivity(), getActivity().getResources().getDimension(R.dimen.dp_2));
        list.addItemDecoration(new SpaceItemDecoration((int) mDp2px));
        list.addOnScrollListener(new RecyclerViewOnScrollListener(getContext()));
        list.setAdapter(imgAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setRow(ChangeRow row) {
        PreferenceManager.getInstance().setRow(row.getRow());
        GridLayoutManager mManager = (GridLayoutManager) list.getLayoutManager();
        mManager.setSpanCount(row.getRow());
        imgAdapter.setItemLayoutId(row.getRow());
        imgAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemChildClick(ViewHolder viewHolder, PicListBean data, int position) {
        int mApid = data.getApid();
        Intent mIntent = new Intent(getActivity(), DetailImgActivity.class);
        mIntent.putExtra(Constants.APID, mApid);
        getActivity().startActivity(mIntent);
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        int mRow = PreferenceManager.getInstance().getRow();
        return new GridLayoutManager(getActivity(), mRow);
    }

    private int loadtime = 0;
    private Map<String, String[]> simpleMap;

    /**
     * 加载更多的回调方法
     *
     * @param isReload 是否是重新加载，只有加载失败后，点击重新加载时为true
     */
    @Override
    public void onLoadMore(boolean isReload) {
        if (type == Contant.HOT) {
            imgPresenter.refreshHot(simpleMap.get("signs")[loadtime], Integer.valueOf(simpleMap.get("lastIndex")[loadtime]));
        } else
            imgPresenter.refreshNew(simpleMap.get("signs")[loadtime], Integer.valueOf(simpleMap.get("lastIndex")[loadtime]));
        loadtime++;
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        if (type == Contant.HOT)
            imgPresenter.refreshHot(Contant.imgsign, -1);
        else imgPresenter.refreshNew(Contant.imgsign, -1);
    }

    @Override
    public void start() {

    }

    @Override
    public void compete() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        imgPresenter.detachView();
    }

    @Override
    public void refreshHot(HotPhoto hotPhoto) {
        if (hotPhoto != null) {
            if (swip.isRefreshing()) {
                picListBeans.addAll(0, hotPhoto.getDataObj().getHotPicList());
            } else
                picListBeans.addAll(picListBeans.size(), hotPhoto.getDataObj().getHotPicList());
            lastIndex = hotPhoto.getDataObj().getLastIndex();
            imgAdapter.notifyDataSetChanged();
            swip.setRefreshing(false);
        }
        swip.setRefreshing(false);
    }

    @Override
    public void refreshNew(NewPhoto newPhoto) {
        if (newPhoto != null) {
            if (swip.isRefreshing()) {
                picListBeans.addAll(0, newPhoto.getDataObj().getNewPicList());
            } else
                picListBeans.addAll(picListBeans.size(), newPhoto.getDataObj().getNewPicList());
            lastIndex = newPhoto.getDataObj().getLastIndex();
            imgAdapter.notifyDataSetChanged();
            swip.setRefreshing(false);
        }
        swip.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
