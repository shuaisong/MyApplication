package com.example.lenovo.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.lenovo.myapplication.R;

/**
 * Created by lenovo on 2019/4/22.
 * auther:lenovo
 * Date：2019/4/22
 */
public class StrokeImageView extends android.support.v7.widget.AppCompatImageView {
    public StrokeImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    private boolean isSelect = false;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isSelect) {
            Rect mRect = new Rect();
            canvas.getClipBounds(mRect);
            mRect.bottom--;
            mRect.top--;
            Paint mPaint = new Paint();
            mPaint.setColor(getResources().getColor(R.color.colorAccent));
            mPaint.setStrokeWidth(getResources().getDimension(R.dimen.dp_3));
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(mRect, mPaint);
        }
    }
}
