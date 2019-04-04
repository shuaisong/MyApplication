package com.example.lenovo.myapplication.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by lenovo on 2018/9/1.
 * auther:lenovo
 * Date：2018/9/1
 */
@SuppressLint("AppCompatCustomView")
public class CustomEditText extends EditText {
    private DrawableLeftListener mLeftListener;
    private DrawableRightListener mRightListener;

    public CustomEditText(Context context) {
        this(context,null);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setDrawableRightListener(new DrawableRightListener() {
            @Override
            public void onDrawableRightClick(View view) {
                if (getCompoundDrawables()[2]!=null)
                setText("");
            }
        });
    }

    public void setDrawableLeftListener(DrawableLeftListener listener) {
        this.mLeftListener = listener;
    }

    public void setDrawableRightListener(DrawableRightListener listener) {
        this.mRightListener = listener;
    }

    public interface DrawableLeftListener {
          void onDrawableLeftClick(View view);
    }

    public interface DrawableRightListener {
          void onDrawableRightClick(View view);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setFocusable();
                if (mRightListener != null) {
                    if (getCompoundDrawables()[2] != null) {
                        boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < ((getWidth() - getPaddingRight())));
                        if (touchable) {
                            try {
                                hideSoftInput();
                                mRightListener.onDrawableRightClick(this);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }


    /**
     * 设置点击EditText右侧图标EditText失去焦点，防止点击EditText右侧图标EditText获得焦点软键盘弹出
     * 如果软键盘此刻为显示状态则强直性隐藏
     */
    private void hideSoftInput() {
        setFocusableInTouchMode(false);
        setFocusable(false);
        InputMethodManager imm = (InputMethodManager) this.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 设置点击EditText输入区域，EditText请求焦点，软键盘弹出，EditText可编辑
     */
    private void setFocusable() {
        setFocusableInTouchMode(true);
        setFocusable(true);
    }
}
