package com.example.lenovo.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myapplication.Adapter.GridDetailImgAdapter;
import com.example.lenovo.myapplication.Adapter.ViewHolder;
import com.example.lenovo.myapplication.App;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.DetailImgUrl;
import com.example.lenovo.myapplication.bean.PicListBean;
import com.example.lenovo.myapplication.bean.SpaceItemDecoration;
import com.example.lenovo.myapplication.db.DBHelper;
import com.example.lenovo.myapplication.interfaces.OnItemClickListener;
import com.example.lenovo.myapplication.utils.ConvertUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GridDetailImgActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.guanggao)
    ImageView mGuanggao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_detail_img);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        DetailImgUrl.DataObjBean mDataObjBean = (DetailImgUrl.DataObjBean) getIntent().getSerializableExtra("DetailImgUrl");
        int mPosition = getIntent().getIntExtra("position", 0);
        assert mDataObjBean != null;
        List<DetailImgUrl.DataObjBean.PicUrlListBean> mPicUrlList = mDataObjBean.getPicUrlList();
        DBHelper.getInstance().init(this);
        PicListBean mBean = DBHelper.getInstance().author().load((long) mPicUrlList.get(0).getApid());
        String title = mBean.getTitle();
          mTitle.setText(title);
        DBHelper.getInstance().close();
        StaggeredGridLayoutManager mManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecycler.setLayoutManager(mManager);
        GridDetailImgAdapter mAdapter = new GridDetailImgAdapter(this, mPicUrlList, mPosition);
        mAdapter.setTag(this);
        float mDp2px = ConvertUtil.dp2px(App.getInstance(), App.getInstance().getResources().getDimension(R.dimen.dp_2));
        mRecycler.addItemDecoration(new SpaceItemDecoration(mDp2px));
        mRecycler.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener<DetailImgUrl.DataObjBean.PicUrlListBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, DetailImgUrl.DataObjBean.PicUrlListBean data, int position) {
                Intent mIntent = new Intent();
                mIntent.putExtra("position",position);
                setResult(Contant.DetailImgResultCode,mIntent);
                finish();
            }
        });
//        mManager.scrollToPositionWithOffset(mPosition,-2);
        mRecycler.smoothScrollToPosition(mPosition);
//        mRecycler.scrollToPosition(mPosition);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
