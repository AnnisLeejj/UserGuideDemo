package com.lee.user_guide.core;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;

/**
 * @author lee
 * @title: GuidePage
 * @description: 控制一个用户引导界面
 * @date 2020/11/12  5:49 下午
 */
public class GuidePageBuilder {

    /**
     * 设置引导界面,id 与 view 互斥
     */
    int mLayoutId;
    View mView;

    /**
     * 设置移除引导界面的view ,mRemoveId 和 mRemoveView 互斥
     */
    int mRemoveId;
    View mRemoveView;

    /**
     * 设置高亮View,mTargetId 和 mTargetView 互斥
     */
    int mTargetId;
    View mTargetView;

    public static GuidePageBuilder newInstance() {
        return new GuidePageBuilder();
    }

    public GuidePageBuilder setView(View view) {
        mView = view;
        clearLayoutId();
        return this;
    }

    public GuidePageBuilder setView(@LayoutRes int layoutId) {
        mLayoutId = layoutId;
        clearView();
        return this;
    }

    /**
     * 如果使用这个方法,引导界面只有点击这个view才会移除,默认是点击整个引导界面可移除
     *
     * @param removeView 点击可以使引导界面消失的view
     * @return
     */
    public GuidePageBuilder setRemoveView(View removeView) {
        this.mRemoveView = removeView;
        return this;
    }

    /**
     * 如果使用这个方法,引导界面只有点击这个view才会移除,默认是点击整个引导界面可移除
     *
     * @param removeId 点击可以使引导界面消失的 view id
     * @return
     */
    public GuidePageBuilder setRemoveView(@IdRes int removeId) {
        this.mRemoveId = removeId;
        return this;
    }

    public GuidePageBuilder setTarget(View targetView) {
        mTargetView = targetView;
        return this;
    }

    public GuidePageBuilder setTarget(@IdRes int targetId) {
        mTargetId = targetId;
        return this;
    }

    private void clearLayoutId() {
        mLayoutId = 0;
    }

    private void clearView() {
        mView = null;
    }
}