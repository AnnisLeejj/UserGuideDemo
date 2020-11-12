package com.lee.user_guide.core;

import android.app.Activity;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import static android.icu.lang.UCharacter.DecompositionType.CIRCLE;

/**
 * @author lee
 * @title: GuideView
 * @description: 用户引导的View
 * @date 2020/11/12  8:40 下午
 */
public class GuideView extends FrameLayout {
    /**
     * 半透明背景
     */
    final int DEFAULT_BACKGROUND_COLOR = 0xb2000000;

    GuidePageBuilder mPageBuilder;
    Controller mController;

    public GuideView(Activity mActivity, GuidePageBuilder pageBuilder, Controller controller) {
        super(mActivity);
        mPageBuilder = pageBuilder;
        mController = controller;
        init();
        createView();
        setClickListener();
    }

    private void init() {
        //ViewGroup默认设定为true，会使onDraw方法不执行，如果复写了onDraw(Canvas)方法，需要清除此标记
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(DEFAULT_BACKGROUND_COLOR);
    }

    View mView;

    private void createView() {
        if (mPageBuilder.mLayoutId != 0) {
            mView = LayoutInflater.from(getContext()).inflate(mPageBuilder.mLayoutId, null, false);
        } else {
            mView = mPageBuilder.mView;
        }
        LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mView, params);
    }

    private void setClickListener() {
        if (mPageBuilder.mRemoveView != null || mPageBuilder.mRemoveId != 0) {
            View removeView;
            if (mPageBuilder.mRemoveId != 0) {
                removeView = findViewById(mPageBuilder.mRemoveId);
            } else {
                removeView = mPageBuilder.mRemoveView;
            }
            if (removeView == null) {
                //I am thinking about whether throw exception when it is null
                viewClick();
            } else {
                //should check the removeView in the guide-view
                removeView.setOnClickListener(v -> {
                    mController.removeGuideView(GuideView.this);
                });
            }
        } else {
            viewClick();
        }
    }

    private void viewClick() {
        setOnClickListener(v -> {
            mController.removeGuideView(GuideView.this);
        });
    }
}