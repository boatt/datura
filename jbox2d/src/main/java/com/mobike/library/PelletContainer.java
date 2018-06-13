package com.mobike.library;


import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by kimi on 2017/7/8 0008.
 * Email: 24750@163.com
 */

public class PelletContainer extends FrameLayout {

    private PelletWorld mPelletWorld;

    public PelletContainer(@NonNull Context context) {
        this(context,null);
    }

    public PelletContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        mPelletWorld = new PelletWorld(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPelletWorld.onSizeChanged(w,h);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mPelletWorld.onLayout(changed);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPelletWorld.onDraw(canvas);
    }

    public PelletWorld getmPelletWorld(){
        return this.mPelletWorld;
    }
}
