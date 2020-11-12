package com.lee.user_guide.core;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * @author lee
 * @title: GuideView
 * @description: 用户引导的View
 * @date 2020/11/12  8:40 下午
 */
public class GuideView extends FrameLayout {
    GuidePageBuilder mPageBuilder;
    Controller mController;

    public GuideView(Activity mActivity, GuidePageBuilder pageBuilder, Controller controller) {
        super(mActivity);
        mPageBuilder = pageBuilder;
        mController = controller;
        createView();
        setClickListener();
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
                removeView= findViewById(mPageBuilder.mRemoveId);
            }else{
                removeView = mPageBuilder.mRemoveView;
            }
            if (removeView == null) {
                //I am thinking about whether throw exception when it is null
                viewClick();
            } else {
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
