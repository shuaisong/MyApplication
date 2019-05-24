package com.example.lenovo.myapplication.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.myapplication.Adapter.VPadapter1;
import com.example.lenovo.myapplication.Contact.DetailImgContact;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseActivity;
import com.example.lenovo.myapplication.bean.DetailImgUrl;
import com.example.lenovo.myapplication.bean.PicListBean;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.component.DaggerActivityComponent;
import com.example.lenovo.myapplication.db.DBHelper;
import com.example.lenovo.myapplication.presenter.DetailImgPresenter;
import com.example.lenovo.myapplication.utils.OSUtils;
import com.example.lenovo.myapplication.utils.StatusBarUtil;
import com.example.lenovo.myapplication.utils.Util;
import com.example.lenovo.myapplication.view.PopupWindowConfig;
import com.example.lenovo.myapplication.view.VerticalViewPager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

@RequiresApi(api = Build.VERSION_CODES.M)
public class DetailImgActivity extends BaseActivity implements DetailImgContact.View {
    @Inject
    DetailImgPresenter presenter;
    @BindView(R.id.detail_vp)
    VerticalViewPager mDetailVp;
    @BindView(R.id.comment)
    EditText mComment;
    @BindView(R.id.comments_num)
    TextView mCommentsNum;
    @BindView(R.id.prise_num)
    TextView mPriseNum;
    @BindView(R.id.collect_num)
    TextView mCollectNum;
    @BindView(R.id.bottom)
    LinearLayout mBottom;
    @BindView(R.id.index)
    TextView mIndex;
    @BindView(R.id.sum)
    TextView mSum;
    @BindView(R.id.title)
    RelativeLayout mTitle;
    @BindView(R.id.guanggao)
    ImageView mGuanggao;
    @BindView(R.id.parent)
    CoordinatorLayout parent;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;

    private List<DetailImgUrl.DataObjBean> list;
    private VPadapter1 VPadapter1;
    private int currentPosition;
    private int mId=1;
    TreeMap<String, String> mMap = new TreeMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mId =  mBundle.getInt("id",1);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        mMap.put("apid",String.valueOf(mId));
        presenter.getPicUrlList(mId, Util.getSign(mMap));
        VPadapter1.setFirstLoad(true);
        Glide.with(this).load("http://pgdt.ugdtimg.com/gdt/0/DAAOR64AKAABkAAVBbY-s_CjGBvNiR.jpg/0?ck=5a7e4aa991813c823b8178724d91ba36")
                .into(mGuanggao);
    }
private int mVerticalOffset;
    @Override
    protected void initViews() {
        presenter.attachView(this);
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, false);
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(android.R.color.black));

       /* //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this,getResources().getColor(android.R.color.transparent));
        }*/
        final WindowManager mManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        final DisplayMetrics mMetrics = new DisplayMetrics();
        mManager.getDefaultDisplay().getMetrics(mMetrics);
        ViewGroup.LayoutParams mParams = mDetailVp.getLayoutParams();
        if (OSUtils.isEmui()&&OSUtils.hasNotchInScreen(this.getApplicationContext())){
            mParams.height = mMetrics.heightPixels-(int)getResources().getDimension(R.dimen.dp_50) ;
        }else {
            mParams.height = mMetrics.heightPixels-(int)getResources().getDimension(R.dimen.dp_50)- StatusBarUtil.getStatusBarHeight(this);
        }
        mDetailVp.setLayoutParams(mParams);

        parent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (-mVerticalOffset<=mMetrics.heightPixels/2){
                        appBarLayout.setExpanded(true);
                    }else{
                        appBarLayout.setExpanded(false);
                        appBarLayout.setVisibility(View.GONE);
                        finish();
                    }
                }
                return false;
            }
        });
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                 mVerticalOffset = verticalOffset;
            }
        });
        list = new ArrayList<>();
        VPadapter1 = new VPadapter1(this);
        mDetailVp.setMinPageOffset(0.4f);
        mDetailVp.setPageMargin((int) getResources().getDimension(R.dimen.dp_100));
        VPadapter1.setOnitemClickListener(new VPadapter1.OnItemClickListener() {
            @Override
            public void onItemClick(int position, DetailImgUrl.DataObjBean.PicUrlListBean bean) {
                setViewVisible(true);
            }
        });
        VPadapter1.setOnItemPageSelectListener(new VPadapter1.OnItemPageSelectListener() {
            @Override
            public void onItemPageSelect(int position, String num) {
                childPagerSelect(position+1,num);
            }
        });
        mDetailVp.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mSum.setText(String.format(DetailImgActivity.this.getString(R.string.sum_num), list.get(position).getPicUrlList().size()));
                int mApid = list.get(currentPosition).getPicUrlList().get(1).getApid();
                DBHelper.getInstance().init(DetailImgActivity.this);
                PicListBean mListBean = DBHelper.getInstance().author().load((long) mApid);
                if (mListBean != null) {
                    mCommentsNum.setText(mListBean.getComment_num());
                    mPriseNum.setText(mListBean.getThumb_num());
                }
                DBHelper.getInstance().getDaoMaster().getDatabase().close();
                DBHelper.getInstance().close();
                setViewVisible(false);
                VPadapter1.setViewAtFirst(currentPosition);
                if (currentPosition < position) {
                        positions.add(position);
                        mId++;
                        mMap.clear();
                        mMap.put("apid",String.valueOf(mId));
                        presenter.getPicUrlList(mId, Util.getSign(mMap));
                }
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Picasso mPicasso = Picasso.get();
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        mPicasso.resumeTag(this);
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        mPicasso.pauseTag(this);
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        mPicasso.pauseTag(this);
                        break;
                }
            }
        });

    }

    private void setViewVisible(boolean visible) {
        if (!visible){
            mTitle.setVisibility(View.INVISIBLE);
            mBottom.setVisibility(View.INVISIBLE);
            return;
        }
            int viewVisible = mTitle.getVisibility()==View.INVISIBLE?View.VISIBLE: View.INVISIBLE;
            mTitle.setVisibility(viewVisible);
            mBottom.setVisibility(viewVisible);
    }

    private void childPagerSelect(int index,String num) {
        mIndex.setText(String.valueOf(index));
        mCollectNum.setText(num);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_detail_img;
    }

    @Override
    public void start() {

    }

    @Override
    public void compete() {

    }
    List<Integer> positions = new ArrayList<>(6);

    @Override
    public void showImg(DetailImgUrl.DataObjBean dataObj) {
        if (VPadapter1.isFirstLoad()){
            mId++;
            mMap.clear();
            mMap.put("apid",String.valueOf(mId));
            presenter.getPicUrlList(mId, Util.getSign(mMap));
        }
        if (!list.contains(dataObj))
            list.add(list.size(), dataObj);
        if (list.size() == 1) {
            mSum.setText(String.format(DetailImgActivity.this.getString(R.string.sum_num), list.get(0).getPicUrlList().size()));
            int mApid = list.get(currentPosition).getPicUrlList().get(1).getApid();

            DBHelper.getInstance().init(DetailImgActivity.this);
            PicListBean mListBean = DBHelper.getInstance().author().load((long) mApid);
            if (mListBean != null) {
                mCommentsNum.setText(mListBean.getComment_num());
                mPriseNum.setText(mListBean.getThumb_num());
            }
            DBHelper.getInstance().getDaoMaster().getDatabase().close();
            DBHelper.getInstance().close();
        }
        VPadapter1.addData(dataObj);

        if (mDetailVp.getAdapter() == null)
            mDetailVp.setAdapter(VPadapter1);
        VPadapter1.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @OnCheckedChanged({R.id.prise, R.id.collect})
    public void OnCheckedChanged(CheckBox view) {
        switch (view.getId()) {
            case R.id.prise:
                Integer mPrise_Num = Integer.valueOf(mPriseNum.getText().toString());
                if (view.isChecked()) {
                    mPriseNum.setText(String.valueOf(mPrise_Num + 1));
                } else mPriseNum.setText(String.valueOf(mPrise_Num - 1));
                break;
            case R.id.collect:
                Integer mCollect_Num = Integer.valueOf(mCollectNum.getText().toString());
                if (view.isChecked()) {
                    mCollectNum.setText(String.valueOf(mCollect_Num + 1));
                } else mCollectNum.setText(String.valueOf(mCollect_Num - 1));
                break;
        }
    }

    @OnClick({R.id.comments, R.id.comment, R.id.download,R.id.multi,R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comments:
                Intent mIntent = new Intent(this, CommentActivity.class);
                mIntent.putExtra("aid", list.get(currentPosition).getPicUrlList().get(0).getApid());
                startActivity(mIntent);
                break;
            case R.id.comment:
                new PopupWindowConfig(this, R.layout.poup_window).getInstance().showAtLocation(mDetailVp, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.download:
                break;
            case R.id.multi:
                DetailImgUrl.DataObjBean mDataObjBean = list.get(currentPosition);
                Intent mIntent1 = new Intent(this,GridDetailImgActivity.class);
                mIntent1.putExtra("DetailImgUrl",mDataObjBean);
                mIntent1.putExtra("position",Integer.valueOf(mIndex.getText().toString()));
                startActivityForResult(mIntent1,Contant.DetailImgRequestCode);
                setViewVisible(false);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data!=null&&requestCode==Contant.DetailImgRequestCode&&resultCode==Contant.DetailImgResultCode){
            int mPosition = data.getIntExtra("position", Integer.valueOf(mIndex.getText().toString()) - 1);
            VPadapter1.setPosition(currentPosition,mPosition);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
