package com.example.lenovo.myapplication.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.lenovo.myapplication.Adapter.CollectionAdapter;
import com.example.lenovo.myapplication.Contact.CollectionContact;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseFragment;
import com.example.lenovo.myapplication.bean.Collection;
import com.example.lenovo.myapplication.bean.ConvertCollection;
import com.example.lenovo.myapplication.bean.MyCollection;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.component.DaggerFragmentComponent;
import com.example.lenovo.myapplication.presenter.CollectionPresenter;
import com.example.lenovo.myapplication.view.PopupWindowConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/4/19.
 * auther:lenovo
 * Date：2018/4/19
 */

public class CollectFG extends BaseFragment implements CollectionContact.View, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
@Inject
    CollectionPresenter presenter;
    private SwipeRefreshLayout swip;
    private Button login;
    private ImageView close;
    private RelativeLayout bottom;
    private CollectionAdapter adapter;
    private List<MyCollection.DataBean> beanList;

    @Override
    protected void initView() {
        presenter.attachView(this);
        swip = rootView.findViewById(R.id.swip);
        login = rootView.findViewById(R.id.login);
        close = rootView.findViewById(R.id.close);
        bottom = rootView.findViewById(R.id.bottom);
        close.setOnClickListener(this);
        login.setOnClickListener(this);
        swip.setOnRefreshListener(this);
        RecyclerView collect_recycle =rootView.findViewById(R.id.collect_recycle);
        GridLayoutManager mManager = new GridLayoutManager(getActivity(), 4);
        collect_recycle.setLayoutManager(mManager);
        beanList = new ArrayList<>();
        adapter = new CollectionAdapter(getActivity(), beanList, true);
        adapter.setTag(this);
//        adapter.setLoadingView(R.layout.sample_common_list_footer_loading);
        collect_recycle.setAdapter(adapter);
    }

    @Override
    protected void onFragmentFirstVisible() {
        swip.measure(0,0);
        swip.setRefreshing(true);
        presenter.getCollectionList("-1","0");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupFragComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fg_collect;
    }

    @Override
    public void showCollection(Collection collection) {
        swip.setRefreshing(false);
        MyCollection mMyCollection = ConvertCollection.convert(collection);
        if (!beanList.containsAll(mMyCollection.getList())){
            beanList.addAll(0,mMyCollection.getList());
            adapter.notifyDataSetChanged();
        }
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
        presenter.detachView();
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        presenter.getCollectionList("-1","0");
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.close:
                bottom.setVisibility(View.INVISIBLE);
                break;
            case  R.id.login:
                PopupWindow mInstance = new PopupWindowConfig(getActivity(),R.layout.poup_window).getInstance();
                mInstance.showAtLocation(swip, Gravity.BOTTOM,0,0);
                break;
        }
    }
}
