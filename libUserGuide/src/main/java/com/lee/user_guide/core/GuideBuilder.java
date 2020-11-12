package com.lee.user_guide.core;

import android.app.Activity;
import android.app.Fragment;

import androidx.annotation.NonNull;

import com.lee.user_guide.error.GuideViewEmptyException;
import com.lee.user_guide.listener.OnGuideChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lee
 */
public class GuideBuilder implements Cloneable {
    /**
     * 引导层显示,移除的监听
     */
    OnGuideChangeListener guideChangeListener;
    /**
     * 引导界面的集合,按照添加顺序显示
     */
    List<GuidePageBuilder> guidePages = new ArrayList<>(5);

    Activity mActivity;

    private GuideBuilder(@NonNull Activity activity) {
        mActivity = activity;
    }

    /**
     * 新手指引入口
     *
     * @param activity activity
     * @return builder对象，设置参数
     */
    public static GuideBuilder with(@NonNull Activity activity) {
        return new GuideBuilder(activity);
    }

    public static GuideBuilder with(@NonNull Fragment fragment) {
        return new GuideBuilder(fragment.getActivity());
    }

    /**
     * 添加引导页
     */
    public GuideBuilder addGuidePage(GuidePageBuilder guidePage) {
        checkGuidePageAvailability(guidePage);
        guidePages.add(guidePage);
        return this;
    }

    /**
     * checking guide view is availability.
     * @param guidePage
     */
    private void checkGuidePageAvailability(GuidePageBuilder guidePage) {
        if (guidePage.mView == null && guidePage.mLayoutId == 0) {
            throw new GuideViewEmptyException();
        }
    }

    public GuideBuilder setGuideChangeListener(OnGuideChangeListener guideChangeListener) {
        this.guideChangeListener = guideChangeListener;
        return this;
    }

    private void setGuidePages(List<GuidePageBuilder> guidePages) {
        this.guidePages = guidePages;
    }

    public List<GuidePageBuilder> getGuidePages() {
        return guidePages;
    }

    /**
     * 深度拷贝,避免使用过程中的改变(有点多余)
     *
     * @return
     */
    @Override
    public GuideBuilder clone() {
        GuideBuilder builder = new GuideBuilder(this.mActivity);
        List<GuidePageBuilder> guidePages = new ArrayList<>(5);
        guidePages.addAll(this.guidePages);
        builder.setGuidePages(guidePages);
        builder.setGuideChangeListener(this.guideChangeListener);
        return builder;
    }

    public Controller show() {
        Controller controller = new Controller(this);
        controller.show();
        return controller;
    }
}
