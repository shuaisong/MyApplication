package com.example.lenovo.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.Button;

import com.example.lenovo.myapplication.Adapter.CommentAdapter;
import com.example.lenovo.myapplication.Contact.CommentContact;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseActivity;
import com.example.lenovo.myapplication.bean.ArticlePicComment;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.component.DaggerActivityComponent;
import com.example.lenovo.myapplication.interfaces.OnLoadMoreListener;
import com.example.lenovo.myapplication.presenter.CommentPresenter;
import com.example.lenovo.myapplication.view.PopupWindowConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2018/8/23.
 * auther:lenovo
 * Date：2018/8/23
 */
public class CommentActivity extends BaseActivity implements CommentContact.View, OnLoadMoreListener {
    @Inject
    CommentPresenter presenter;
    @BindView(R.id.recycler_comment)
    RecyclerView mRecyclerComment;
    @BindView(R.id.swipe_comment)
    SwipeRefreshLayout mSwipeComment;
    @BindView(R.id.send)
    Button mSend;
    private List<ArticlePicComment.DataObjBean.ArticleCommentListBean> commentList;
    private CommentAdapter adapter;
    private String[] aids = Contant.getArticleSampleList().get("aid");
    private String[] signs = Contant.getArticleSampleList().get("signs");
    private String[] signs2 = Contant.getArticleSampleList().get("signs2");
    private int aid = 0;
    private int page = 1;
    private int index = 0;

    @Override
    protected void initData() {
        Intent mIntent = getIntent();

        if (mIntent != null) {
            Bundle mExtras = mIntent.getExtras();
            assert mExtras != null;
            aid = mExtras.getInt("aid");
        }
        for (int i = 0; i < aids.length; i++) {
            if (aids[i].equals(String.valueOf(aid))) {
                index = i;
            }
        }
        presenter.getArticleCommentDetail(signs[index], String.valueOf(aid), "1", String.valueOf(page));
    }

    @Override
    protected void initViews() {
        presenter.attachView(this);
        commentList = new ArrayList<>();
        adapter = new CommentAdapter(this, commentList, true);
        adapter.setOnLoadMoreListener(this);
        LinearLayoutManager mManager = new LinearLayoutManager(this);
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerComment.setLayoutManager(mManager);
        mRecyclerComment.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerComment.setAdapter(adapter);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_comment;
    }

    @Override
    public void showComment(ArticlePicComment.DataObjBean dataObj) {
        if (mSwipeComment.isRefreshing()) {
            if (!commentList.containsAll(dataObj.getArticleCommentList())) {
                commentList.addAll(0, dataObj.getArticleCommentList());
                adapter.notifyDataSetChanged();
            }
            mSwipeComment.setRefreshing(false);
        } else {
            page++;
            commentList.addAll(dataObj.getArticleCommentList());
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
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    /**
     * 加载更多的回调方法
     *
     * @param isReload 是否是重新加载，只有加载失败后，点击重新加载时为true
     */
    @Override
    public void onLoadMore(boolean isReload) {
        if (!adapter.isLoadEnd) {
            presenter.getArticleCommentDetail(signs2[index], String.valueOf(aid), "1", String.valueOf(page));
            adapter.loadEnd();
        }
    }

    @OnClick(R.id.comment)
    public void onViewClicked() {
        new PopupWindowConfig(this,R.layout.poup_window).getInstance().showAtLocation(mSwipeComment, Gravity.BOTTOM,0,0);
    }
}
