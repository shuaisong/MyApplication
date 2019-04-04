package com.example.lenovo.myapplication.view.fragment;

import android.content.Intent;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.base.BaseFragment;
import com.example.lenovo.myapplication.bean.msg.ChangeRow;
import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.component.DaggerFragmentComponent;
import com.example.lenovo.myapplication.utils.PreferenceManager;
import com.example.lenovo.myapplication.view.activity.LogInActivity;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by lenovo on 2018/4/19.
 * auther:lenovo
 * Date：2018/4/19
 */

public class MineFG extends BaseFragment  {

    private Switch switch_small,switch_private;
    private boolean small_checked = false;
    private boolean login;
    private boolean back;

    @Override
    protected void setupFragComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fg_mine;
    }
@Override
    protected void initView() {
    switch_small = rootView.findViewById(R.id.switch_small);
    switch_private = rootView.findViewById(R.id.switch_private);
    int mRow = PreferenceManager.getInstance().getRow();
        if (mRow == 1) {
            switch_small.setChecked(false);
            small_checked =false;
        } else {
            switch_small.setChecked(true);
            small_checked =true;
        }
    }
    /*@Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getLogin(Login login){
        if (!login.isLogin()){
            switch_private.setChecked(false);
        }
    }*/
    @Override
    protected void initData() {
      //  EventBus.getDefault().register(this);
        login = PreferenceManager.getInstance().getLogin();
        back = false;
        switch_private.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!login&&!back){
                    startActivityForResult(new Intent(getContext(),LogInActivity.class),102);
                }else {

                }
            }
        });
    }

    /**
     * 去除setUserVisibleHint()多余的回调场景，保证只有当fragment可见状态发生变化时才回调
     * 回调时机在view创建完后，所以支持ui操作，解决在setUserVisibleHint()里进行ui操作有可能报null异常的问题
     * <p>
     * 可在该回调方法里进行一些ui显示与隐藏，比如加载框的显示和隐藏
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (!isVisible){
            if (small_checked!=switch_small.isChecked()){
                small_checked =switch_small.isChecked();
                int row = switch_small.isChecked() ? 2 : 1;
                PreferenceManager.getInstance().setRow(row);
                ChangeRow mChangeRow = new ChangeRow();
                mChangeRow.setRow(row);
               EventBus.getDefault().post(mChangeRow);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==102&&resultCode==102){
            back = true;
            boolean mLogin = data.getBooleanExtra("login", false);
            switch_private.setChecked(mLogin);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
