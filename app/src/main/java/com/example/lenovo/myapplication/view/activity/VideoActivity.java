package com.example.lenovo.myapplication.view.activity;

import android.content.pm.ActivityInfo;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myapplication.Adapter.CommentAdapter;
import com.example.lenovo.myapplication.Adapter.RecomandVideoAdapter;
import com.example.lenovo.myapplication.Adapter.ViewHolder;
import com.example.lenovo.myapplication.Contact.DetailVideoContact;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseActivity;
import com.example.lenovo.myapplication.bean.ArticlePicComment;
import com.example.lenovo.myapplication.bean.RecommandVideo;
import com.example.lenovo.myapplication.bean.SpaceItemDecoration;
import com.example.lenovo.myapplication.bean.VideoDetail;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.component.DaggerActivityComponent;
import com.example.lenovo.myapplication.interfaces.OnItemClickListener;
import com.example.lenovo.myapplication.presenter.DetailVideoPresenter;
import com.example.lenovo.myapplication.utils.PreferenceManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoActivity extends BaseActivity implements DetailVideoContact.View, View.OnClickListener {
    @Inject
    DetailVideoPresenter presenter;
    @BindView(R.id.player)
    JZVideoPlayerStandard mPlayer;
    @BindView(R.id.video_title)
    TextView mTitle;
    @BindView(R.id.play_count)
    TextView mPlayCount;
    @BindView(R.id.video_recycler)
    RecyclerView mVideoRecycler;
    @BindView(R.id.commend_recycler)
    RecyclerView mCommendRecycler;
    @BindView(R.id.comment)
    EditText mComment;
    @BindView(R.id.comments)
    ImageView mComments;
    @BindView(R.id.comments_num)
    TextView mCommentsNum;
    @BindView(R.id.prise)
    CheckBox mPrise;
    @BindView(R.id.prise_num)
    TextView mPriseNum;
    @BindView(R.id.collect)
    CheckBox mCollect;
    @BindView(R.id.collect_num)
    TextView mCollectNum;
    List<RecommandVideo.DataObjBean.HotPicListBean> recommandVideoList;
    RecomandVideoAdapter mRecomandVideoAdapter;

    @Override
    protected void initData() {
        presenter.getArticleVideoDetail("5067", Contant.getArticleVideoDetailSign);
        presenter.getRecommandVideoList(Contant.getRecommandVideoListSign);
    }

    @Override
    protected void initViews() {
        presenter.attachView(this);
        recommandVideoList = new ArrayList<>();
        mRecomandVideoAdapter = new RecomandVideoAdapter(this, recommandVideoList, false);
        mRecomandVideoAdapter.setOnItemClickListener(new OnItemClickListener<RecommandVideo.DataObjBean.HotPicListBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, RecommandVideo.DataObjBean.HotPicListBean data, int position) {
                configPlayer(data.getTitle(),data.getCover_url(),data.getVideo_url(),data.getClick_num(),data.getThumb_num(),data.getCollect_num());
            }
        });
        LinearLayoutManager mManager = new LinearLayoutManager(this);
        mVideoRecycler.setLayoutManager(mManager);
        mVideoRecycler.setAdapter(mRecomandVideoAdapter);
        mVideoRecycler.addItemDecoration(new SpaceItemDecoration(10));


        mCommendRecycler.setLayoutManager(new LinearLayoutManager(this));
        List<ArticlePicComment.DataObjBean.ArticleCommentListBean> mCommentListBeans = new ArrayList<>();
        CommentAdapter mCommentAdapter = new CommentAdapter(this, mCommentListBeans, false);
        View emptyView = LayoutInflater.from(this).inflate(R.layout.no_comment, null);
        ViewGroup.LayoutParams mLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        emptyView.setLayoutParams(mLayoutParams);
        mCommentAdapter.setEmptyView(emptyView);
        mCommendRecycler.setAdapter(mCommentAdapter);
        mPlayer.backButton.setOnClickListener(this);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_video;
    }

    @Override
    public void start() {

    }

    @Override
    public void compete() {

    }

    @Override
    public void showVideo(VideoDetail.DataObjBean dataObj) {
        configPlayer(dataObj.getTitle(),dataObj.getCover_url(),dataObj.getVideo_url(),dataObj.getClick_num(),dataObj.getThumb_num(),dataObj.getCollect_num());
    }

    private void configPlayer(String title, String cover_url, String video_url, int click_num, int thumb_num, int collect_num) {
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        mPlayer.setUp(video_url, JZVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN);
        String coverUri = PreferenceManager.getInstance().getVideoUrlPrefix() +cover_url;
        Picasso.get().load(coverUri).placeholder(R.mipmap.video_default).into(mPlayer.thumbImageView);
        mTitle.setText(title);
        mPlayCount.setText(String.format(getString(R.string.video_n_count), click_num));
        mPriseNum.setText(String.format(getString(R.string.num),thumb_num));
        mCollectNum.setText(String.format(getString(R.string.num),collect_num));
    }

    @Override
    public void showRecommandVideo(RecommandVideo.DataObjBean dataObj) {
        if (dataObj!=null){
            recommandVideoList.addAll(dataObj.getHotPicList());
            mRecomandVideoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void finish() {
        super.finish();
        presenter.detachView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayerStandard.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
       if (JZVideoPlayerStandard.backPress()){
           return;
       }
       super.onBackPressed();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        finish();
    }
}
