package com.example.lenovo.myapplication.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.lenovo.myapplication.Adapter.HotSearch2Adapter;
import com.example.lenovo.myapplication.Adapter.HotSearchAdapter;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.SearchContent21;
import com.example.lenovo.myapplication.bean.SearchImgListBean;
import com.example.lenovo.myapplication.bean.SearchType;
import com.example.lenovo.myapplication.bean.SearchVideoListBean;
import com.example.lenovo.myapplication.bean.SpaceItemDecoration;
import com.example.lenovo.myapplication.interfaces.OnLoadMoreListener;
import com.example.lenovo.myapplication.request.CallBack;
import com.example.lenovo.myapplication.utils.PreferenceManager;
import com.example.lenovo.myapplication.utils.StatusBarUtil;
import com.example.lenovo.myapplication.utils.Util;
import com.example.lenovo.myapplication.view.CustomEditText;
import com.vise.xsnow.http.ViseHttp;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HotSearchActivity extends AppCompatActivity implements OnLoadMoreListener {

    @BindView(R.id.et_search)
    CustomEditText mEtSearch;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.swip)
    SwipeRefreshLayout mSwip;
    @BindView(R.id.hot_grid)
    GridView mHotGrid;
    @BindView(R.id.hot_search)
    ConstraintLayout mHotSearch;
    private int search_type;
    private HotSearch2Adapter<SearchImgListBean> search1Adapter;
    private List<SearchImgListBean> searchImgListBeans;
    private HotSearch2Adapter<SearchVideoListBean> search2Adapter;
    private List<SearchVideoListBean> searchVideoListBeans;
    private HotSearchAdapter hotImgSearchAdapter;
    private HotSearchAdapter hotVideoSearchAdapter;
    private SearchType.DataObjBean.KeywordsListBean keywordsListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_search);
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);        StatusBarUtil.setStatusBarDarkTheme(this, false);

        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.colorAccent));

        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        search_type = getIntent().getIntExtra("type", 1);
        GridLayoutManager mManager = new GridLayoutManager(HotSearchActivity.this, 1);
        mRecycler.setLayoutManager(mManager);
        final Drawable drawable_right= getResources().getDrawable(R.mipmap.icon_close_video);
        drawable_right.setBounds(0,0,drawable_right.getMinimumWidth(),drawable_right.getMinimumHeight());
        final Drawable drawable_left ;
        if (search_type==1)drawable_left = getResources().getDrawable(R.mipmap.icon_search_photo);
        else drawable_left = getResources().getDrawable(R.mipmap.icon_search_title_video);
        drawable_left.setBounds(0,0,drawable_left.getMinimumWidth(),drawable_left.getMinimumHeight());
       mEtSearch. setCompoundDrawables(drawable_left,null,null,null);
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0) {
                    mEtSearch.setCompoundDrawables(drawable_left, null, drawable_right, null);
                } else {
                  mEtSearch.setCompoundDrawables(drawable_left, null, null, null);
                }
            }
        });

        if (1 == search_type) {
            mEtSearch.setHint(R.string.search_img);
            if (hotImgSearchAdapter == null) {
                SearchType.DataObjBean mSearchKey_img = PreferenceManager.getInstance().<SearchType.DataObjBean.KeywordsListBean>getSearchKey("1");
                hotImgSearchAdapter = new HotSearchAdapter(this, mSearchKey_img.getKeywordsList());
            }
            mHotGrid.setAdapter(hotImgSearchAdapter);
        } else {
            mEtSearch.setHint(R.string.search_video);
            if (hotVideoSearchAdapter == null) {
                SearchType.DataObjBean mSearchKey_video = PreferenceManager.getInstance().<SearchType.DataObjBean.KeywordsListBean>getSearchKey("2");
                hotVideoSearchAdapter = new HotSearchAdapter(this, mSearchKey_video.getKeywordsList());
            }
            mHotGrid.setAdapter(hotVideoSearchAdapter);
        }
        mHotGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView mTextView = new TextView(HotSearchActivity.this);
                keywordsListBean = (SearchType.DataObjBean.KeywordsListBean) mHotGrid.getAdapter().getItem(position);
                mTextView.setText(String.format("搜索\"%s\"", keywordsListBean.getKeywords()));
                setVisible();
                mEtSearch.setText(keywordsListBean.getKeywords());
                mRecycler.addItemDecoration(new SpaceItemDecoration(8));
                if (search_type == 1) {
                    searchImgListBeans = new ArrayList<>();
                    search1Adapter = new HotSearch2Adapter<>(HotSearchActivity.this, search_type, searchImgListBeans, true, true);
                    search1Adapter.setHeadView(mTextView);
                    search1Adapter.setLoadFailedView(R.layout.loadfail);
                    search1Adapter.setLoadEndView(R.layout.sample_common_list_footer_end);
                    search1Adapter.setLoadingView(R.layout.sample_common_list_footer_loading);
                    search1Adapter.setOnLoadMoreListener(HotSearchActivity.this);
                    mRecycler.setAdapter(search1Adapter);
                    search1Adapter.setStatus(Contant.fresh);
                } else {
                    searchVideoListBeans = new ArrayList<>();
                    search2Adapter = new HotSearch2Adapter<>(HotSearchActivity.this, search_type, searchVideoListBeans, true, true);
                    search2Adapter.setHeadView(mTextView);
                    search2Adapter.setLoadFailedView(R.layout.loadfail);
                    search2Adapter.setLoadEndView(R.layout.sample_common_list_footer_end);
                    search2Adapter.setLoadingView(R.layout.sample_common_list_footer_loading);
                    search2Adapter.setOnLoadMoreListener(HotSearchActivity.this);
                    mRecycler.setAdapter(search2Adapter);
                    search2Adapter.setStatus(Contant.fresh);
                }
                setMap(String.valueOf(search_type),"-1",keywordsListBean.getKeywords());
                searchContent21(search_type, -1, keywordsListBean.getKeywords(),  Util.getSign(map));
            }
        });

        mSwip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (search_type==1){
                    search1Adapter.setStatus(Contant.fresh);
                }else {
                    search2Adapter.setStatus(Contant.fresh);
                }
                setMap(String.valueOf(search_type),"-1",keywordsListBean.getKeywords());
                searchContent21(search_type, -1, keywordsListBean.getKeywords(),  Util.getSign(map));            }
        });
    }
    private int lastId = -1;
    TreeMap<String,String> map = new TreeMap<>();

    private void setMap(String contentType,String lastId,String keywords){
        map.clear();
        map.put("contentType",contentType);
        map.put("lastId",lastId);
        map.put("pageSize","12");
        map.put("keywords",keywords);
    }
    private void setVisible() {
        if (mHotSearch.getVisibility() == View.VISIBLE) {
            mHotSearch.setVisibility(View.GONE);
        } else
            mHotSearch.setVisibility(View.VISIBLE);

        if (mSwip.getVisibility() == View.VISIBLE) {
            mSwip.setVisibility(View.GONE);
        } else
            mSwip.setVisibility(View.VISIBLE);

    }

    private void searchContent21(int contentType, final int lastId, String keywords, String sign) {
        ViseHttp.GET(Contant.searchContent21).addHeader(Contant.sign, sign)
                .addHeader(Contant.token, Contant.tokenValue).addParam("contentType", String.valueOf(contentType))
                .addParam("lastId", String.valueOf(lastId)).addParam("pageSize", String.valueOf(12))
                .addParam("keywords", keywords).request(new CallBack<SearchContent21>() {
            @Override
            public void onSuccess(SearchContent21 searchContent21) {
                if (searchContent21.getCode()!=200){
                    showSearch(null);
                }else {
                    showSearch(searchContent21.getDataObj());
                }
            }
        });
    }

    private void showSearch(SearchContent21.DataObjBean dataObj) {
        mSwip.setRefreshing(false);
        if (dataObj!=null)
        if (search_type == 1) {
            if (search1Adapter.getStatus()==Contant.fresh){
                mSwip.setRefreshing(false);
                if (!searchImgListBeans.containsAll(dataObj.getArticleList())){
                    searchImgListBeans.clear();
                    searchImgListBeans.addAll(dataObj.getArticleList());
                }
            }else {
                searchImgListBeans.addAll(dataObj.getArticleList()) ;
                if (dataObj.getArticleList().size()<12){
                    search1Adapter.setmOpenLoadMore(false);
                }
            }
            lastId =searchImgListBeans.get(searchImgListBeans.size()-1).getApid();
            search1Adapter.setStatus(Contant.normal);
            search1Adapter.notifyDataSetChanged();
        } else {
            searchVideoListBeans.clear();
            searchVideoListBeans.addAll(dataObj.getVideoList());
            search2Adapter.notifyDataSetChanged();
                if (search2Adapter.getStatus()==Contant.fresh){
                    mSwip.setRefreshing(false);
                    if (!searchVideoListBeans.containsAll(dataObj.getVideoList())){
                        searchVideoListBeans.clear();
                        searchVideoListBeans.addAll(dataObj.getVideoList());
                    }
                }else {
                    searchVideoListBeans.addAll(dataObj.getVideoList()) ;
                    if (dataObj.getArticleList().size()<12){
                        search2Adapter.setmOpenLoadMore(false);
                    }
                }
                lastId =searchVideoListBeans.get(searchVideoListBeans.size()-1).getAvid();
                search2Adapter.setStatus(Contant.normal);
                search2Adapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        if (mSwip.getVisibility() == View.VISIBLE) {
            setVisible();
        } else
            finish();
    }

    @Override
    public void onBackPressed() {
        if (mSwip.getVisibility() == View.VISIBLE) {
            setVisible();
        } else
            super.onBackPressed();
    }

    /**
     * 加载更多的回调方法
     *
     * @param isReload 是否是重新加载，只有加载失败后，点击重新加载时为true
     */
    @Override
    public void onLoadMore(boolean isReload) {
        if (search_type==1) {
            search1Adapter.setStatus(Contant.load);
        }
        else{
            search2Adapter.setStatus(Contant.load);
        }
        setMap(String.valueOf(search_type),String.valueOf(lastId)  ,keywordsListBean.getKeywords());
        searchContent21(search_type, lastId, keywordsListBean.getKeywords(), Util.getSign(map));
    }
}
