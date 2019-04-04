package com.example.lenovo.myapplication.view.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.lenovo.myapplication.Adapter.GridAdapter;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseActivity;
import com.example.lenovo.myapplication.bean.RecommendLabel;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.component.DaggerActivityComponent;
import com.example.lenovo.myapplication.utils.PreferenceManager;
import com.example.lenovo.myapplication.utils.StatusBarUtil;
import com.example.lenovo.myapplication.view.CustomEditText;
import com.example.lenovo.myapplication.view.LabelActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity  {

    @BindView(R.id.grid_img)
    GridView mGridImg;
    @BindView(R.id.grid_video)
    GridView mGridVideo;
    @BindView(R.id.et_search)
    CustomEditText mEtSearch;

    @Override
    protected void initData() {
        final RecommendLabel.DataObjBean mDataObjBean = PreferenceManager.getInstance().getRecommendLabelList();
        mGridImg.setNumColumns(mDataObjBean.getArticleLabelList().size()+1);
        mGridVideo.setNumColumns(mDataObjBean.getVideoLabelList().size()+1);
        final GridAdapter imgAdapter = new GridAdapter<>(this, mDataObjBean.getArticleLabelList());
        final GridAdapter videoAdapter = new GridAdapter<>(this, mDataObjBean.getVideoLabelList());
        mGridImg.setAdapter(imgAdapter);
        mGridImg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==mDataObjBean.getArticleLabelList().size()){
                    Intent mIntent = new Intent(SearchActivity.this, LabelActivity.class);
                    mIntent.putExtra("type",1);
                    startActivity(mIntent);
                }else {
                RecommendLabel.DataObjBean.ArticleLabelListBean mBean= (RecommendLabel.DataObjBean.ArticleLabelListBean) imgAdapter.getItem(position);
                Intent mIntent = new Intent(SearchActivity.this, Img_Video_Result.class);
                mIntent.putExtra("ListBean",mBean);
                startActivity(mIntent);}
            }
        });
        mGridVideo.setAdapter(videoAdapter);
        mGridVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==mDataObjBean.getVideoLabelList().size()){
                    Intent mIntent = new Intent(SearchActivity.this, LabelActivity.class);
                    mIntent.putExtra("type",2);
                    startActivity(mIntent);
                }else {
                RecommendLabel.DataObjBean.VideoLabelListBean mBean= (RecommendLabel.DataObjBean.VideoLabelListBean) videoAdapter.getItem(0);
                Intent mIntent = new Intent(SearchActivity.this, Img_Video_Result.class);//2:video
                mIntent.putExtra("ListBean",mBean);
                startActivity(mIntent);
            }}
        });
    }

    @Override
    protected void initViews() {
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);        StatusBarUtil.setStatusBarDarkTheme(this, false);

        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.colorAccent));


        final Drawable drawable_right= getResources().getDrawable(R.mipmap.icon_close_video);
        drawable_right.setBounds(0,0,drawable_right.getMinimumWidth(),drawable_right.getMinimumHeight());
        final Drawable drawable_left= getResources().getDrawable(R.mipmap.ic_search);
        drawable_left.setBounds(0,0,drawable_left.getMinimumWidth(),drawable_left.getMinimumHeight());
        mEtSearch.setCompoundDrawables(drawable_left,null,null,null);
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length()>0){
                    mEtSearch.setCompoundDrawables(drawable_left,null,drawable_right,null);
                }else {
                    mEtSearch.setCompoundDrawables(drawable_left,null,null,null);
                }
            }
        });

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.video, R.id.img,R.id.back})
    public void onViewClicked(View view) {
        Intent  mIntent = new Intent(this,HotSearchActivity.class);
        switch (view.getId()) {
            case R.id.img:
                mIntent.putExtra("type",1);
                startActivity(mIntent);
                break;
            case R.id.video:
                mIntent.putExtra("type",2);
                startActivity(mIntent);
                break;
            case R.id.back:
              finish();
                break;
        }
    }
}
