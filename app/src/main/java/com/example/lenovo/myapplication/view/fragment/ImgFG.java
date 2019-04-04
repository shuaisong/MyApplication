package com.example.lenovo.myapplication.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lenovo.myapplication.Adapter.ImgAdapter;
import com.example.lenovo.myapplication.Adapter.ViewHolder;
import com.example.lenovo.myapplication.App;
import com.example.lenovo.myapplication.Contact.ImgFGContact;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseFragment;
import com.example.lenovo.myapplication.bean.HotPhoto;
import com.example.lenovo.myapplication.bean.NewPhoto;
import com.example.lenovo.myapplication.bean.PicListBean;
import com.example.lenovo.myapplication.bean.SpaceItemDecoration;
import com.example.lenovo.myapplication.bean.msg.ChangeRow;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.component.DaggerFragmentComponent;
import com.example.lenovo.myapplication.db.DBHelper;
import com.example.lenovo.myapplication.interfaces.OnItemChildClickListener;
import com.example.lenovo.myapplication.interfaces.OnLoadMoreListener;
import com.example.lenovo.myapplication.interfaces.RecyclerViewOnScrollListener;
import com.example.lenovo.myapplication.presenter.ImgPresenter;
import com.example.lenovo.myapplication.utils.ConvertUtil;
import com.example.lenovo.myapplication.utils.LogUtil;
import com.example.lenovo.myapplication.utils.PreferenceManager;
import com.example.lenovo.myapplication.utils.Util;
import com.example.lenovo.myapplication.view.activity.DetailImgActivity;
import com.example.lenovo.myapplication.view.activity.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public class ImgFG extends BaseFragment implements ImgFGContact.View,
        OnItemChildClickListener<PicListBean>, OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @Inject
    ImgPresenter imgPresenter;
    private int type;
    private List<PicListBean> picListBeans;
    private RecyclerView list;
    private SwipeRefreshLayout swip;
    private int lastIndex = -1;
    private View loadFailed;
    private MainActivity mainActivity;
    private ImgAdapter imgAdapter;

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
        if (getArguments() != null)
            type = getArguments().getInt("type");

    }

    @Override
    protected int getLayoutID() {
        return R.layout.imgfg;
    }

    @Override
    protected void setupFragComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    protected void onFragmentFirstVisible() {
        swip.measure(0, 0);
        swip.setRefreshing(true);
        if (!checkNet()) {
            loadFailed.setVisibility(View.VISIBLE);
        }
        imgAdapter.setStatus(Contant.fresh);
        if (type == Contant.HOT){
            LogUtil.d("onFragmentFirstVisible :hot");
            imgPresenter.refreshHot(Contant.imgsign, lastIndex);
        }
        else{
            LogUtil.d("onFragmentFirstVisible :new");
            imgPresenter.refreshNew(Contant.imgsign, lastIndex);
        }
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        imgPresenter.attachView(this);
        swip = rootView.findViewById(R.id.swip);
        loadFailed = rootView.findViewById(R.id.loadFailed);
        swip.setColorSchemeColors(mainActivity.getResources().getColor(R.color.colorPrimary),
                mainActivity.getResources().getColor(R.color.colorAccent),
                mainActivity.getResources().getColor(R.color.black_overlay));
        swip.setOnRefreshListener(this);
        list = rootView.findViewById(R.id.list);
        picListBeans = new ArrayList<>();
        int mRow = PreferenceManager.getInstance().getRow();
        imgAdapter = new ImgAdapter(getActivity(), picListBeans, true);
        configAdapter(imgAdapter, mRow);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), mRow);
        list.setLayoutManager(mLayoutManager);
        float mDp2px = ConvertUtil.dp2px(App.getInstance(), App.getInstance().getResources().getDimension(R.dimen.dp_2));
        list.addItemDecoration(new SpaceItemDecoration((int) mDp2px));
        list.addOnScrollListener(new RecyclerViewOnScrollListener(getContext()));
        list.setAdapter(imgAdapter);
    }

    private void configAdapter(ImgAdapter adapter, int mRow) {
        adapter.setItemLayoutId(mRow);
        adapter.setOnItemChildClickListener(R.id.photo_list_item_image, this);
        adapter.setLoadFailedView(R.layout.loadfail);
        adapter.setLoadEndView(R.layout.sample_common_list_footer_end);
        adapter.setLoadingView(R.layout.sample_common_list_footer_loading);
        adapter.setOnLoadMoreListener(this);
        adapter.setTag(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setRow(ChangeRow row) {
        GridLayoutManager mGridLayoutManager = (GridLayoutManager) list.getLayoutManager();
        mGridLayoutManager.setSpanCount(row.getRow());
        imgAdapter.setItemLayoutId(row.getRow());
        list.setAdapter(imgAdapter);
    }

    @Override
    public void onItemChildClick(ViewHolder viewHolder, PicListBean data, int position) {
        Intent mIntent = new Intent(getActivity(), DetailImgActivity.class);
        mIntent.putExtra("id", data.getApid());
        if (getActivity() != null)
            getActivity().startActivity(mIntent);
    }

    /**
     * 加载更多的回调方法
     *
     * @param isReload 是否是重新加载，只有加载失败后，点击重新加载时为true
     */
    @Override
    public void onLoadMore(boolean isReload) {
        imgAdapter.setStatus(Contant.load);
        TreeMap<String, String> mMap = new TreeMap<>();
        mMap.put("lastIndex",String.valueOf(lastIndex));
        if (type == Contant.HOT) {
            imgPresenter.refreshHot(Util.getSign(mMap), lastIndex);
        } else
            imgPresenter.refreshNew(Util.getSign(mMap), lastIndex);
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        if (imgAdapter.getStatus()==Contant.load){
            swip.setRefreshing(false);
            return;
        }
        imgAdapter.setStatus(Contant.fresh);
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
        mainActivity = null;
    }

    @Override
    public void refreshHot(HotPhoto hotPhoto) {
        imgAdapter.setStatus(Contant.normal);
        if (hotPhoto != null) {
            if (loadFailed.getVisibility() == View.VISIBLE) {
                loadFailed.setVisibility(View.GONE);
            }
            if (swip.isRefreshing()) {
                picListBeans.clear();
                picListBeans.addAll(hotPhoto.getDataObj().getHotPicList());
            } else
                picListBeans.addAll(picListBeans.size(), hotPhoto.getDataObj().getHotPicList());
            lastIndex = hotPhoto.getDataObj().getLastIndex();
            list.getAdapter().notifyDataSetChanged();
            swip.setRefreshing(false);
            DBHelper.getInstance().init(getContext());
            for (PicListBean b :
                    hotPhoto.getDataObj().getHotPicList()) {
                PicListBean mLoad = DBHelper.getInstance().author().load((long) b.getApid());
                if (mLoad == null) {
                    DBHelper.getInstance().author().insert(b);
                } else {
                    DBHelper.getInstance().author().update(b);
                }
            }
            DBHelper.getInstance().getDaoMaster().getDatabase().close();
            DBHelper.getInstance().close();
        }
        swip.setRefreshing(false);
    }

    @Override
    public void refreshNew(NewPhoto newPhoto) {
        imgAdapter.setStatus(Contant.normal);
        if (newPhoto != null) {
            if (loadFailed.getVisibility() == View.VISIBLE) {
                loadFailed.setVisibility(View.GONE);
            }
            if (swip.isRefreshing()) {
                picListBeans.clear();
                picListBeans.addAll(newPhoto.getDataObj().getNewPicList());
            } else
                picListBeans.addAll(picListBeans.size(), newPhoto.getDataObj().getNewPicList());
            lastIndex = newPhoto.getDataObj().getLastIndex();
            list.getAdapter().notifyDataSetChanged();
            swip.setRefreshing(false);
            DBHelper.getInstance().init(getContext());
            for (PicListBean b :
                    newPhoto.getDataObj().getNewPicList()) {
                PicListBean mLoad = DBHelper.getInstance().author().load((long) b.getApid());
                if (mLoad == null) {
                    DBHelper.getInstance().author().insert(b);
                } else {
                    DBHelper.getInstance().author().update(b);
                }
            }
            DBHelper.getInstance().getDaoMaster().getDatabase().close();
            DBHelper.getInstance().close();
        }
        swip.setRefreshing(false);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
