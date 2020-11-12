package com.lee.user_guide.core;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.lee.user_guide.error.NoGuidePageException;

import java.util.Objects;

/**
 * @author lee
 * @title: Controller
 * @description: 持有GuideBuilder, 并对其进行控制 以 展示用户引导界面
 * @date 2020/11/12  7:43 下午
 */
public class Controller {

    /**
     * 创建的GuideBuilder,根据设置对用户进行引导
     */
    GuideBuilder mGuideBuilder;
    /**
     * 用于添加用户引导界面的根View
     */
    FrameLayout mDecorView;

    /**
     * 深度拷贝,防止在使用过程的数据改变
     *
     * @param guideBuilder
     */
    public Controller(@NonNull GuideBuilder guideBuilder) {
        mGuideBuilder = guideBuilder.clone();
        checkAvailability();

        View anchor = mGuideBuilder.mActivity.findViewById(android.R.id.content);
        if (anchor instanceof FrameLayout) {
            mDecorView = (FrameLayout) anchor;
        } else {
            FrameLayout frameLayout = new FrameLayout(mGuideBuilder.mActivity);
            ViewGroup parent = (ViewGroup) anchor.getParent();
            int indexOfChild = parent.indexOfChild(anchor);
            parent.removeView(anchor);
            if (indexOfChild >= 0) {
                parent.addView(frameLayout, indexOfChild, anchor.getLayoutParams());
            } else {
                parent.addView(frameLayout, anchor.getLayoutParams());
            }
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            frameLayout.addView(anchor, lp);
            mDecorView = frameLayout;
        }
    }

    /**
     * 对mGuideBuilder检查其可用性,如果不可用会抛出错误
     */
    private void checkAvailability() {
        if (Objects.requireNonNull(mGuideBuilder.getGuidePages()).isEmpty()) {
            throw new NoGuidePageException();
        }
    }

    /**
     * 当前显示的引导界面的index
     */
    int current = 0;

    public void show() {
        mDecorView.post(() -> {
            showGuidePage();
            if (mGuideBuilder.guideChangeListener != null) {
                mGuideBuilder.guideChangeListener.onShowed(Controller.this);
            }
//            addListenerFragment();
        });
    }

    /**
     * 显示current引导页
     */
    private void showGuidePage() {
        GuidePageBuilder pageBuilder = mGuideBuilder.guidePages.get(current);
        GuideView guideView = new GuideView(mGuideBuilder.mActivity, pageBuilder, this);

//        guideLayout.setOnGuideLayoutDismissListener(new GuideLayout.OnGuideLayoutDismissListener() {
//            @Override
//            public void onGuideLayoutDismiss(GuideLayout guideLayout) {
//                showNextOrRemove();
//            }
//        });
        mDecorView.addView(guideView, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        currentLayout = guideLayout;
//        if (onPageChangedListener != null) {
//            onPageChangedListener.onPageChanged(current);
//        }
    }

    /**
     * removing the guide view after user did clicked the remove-view
     *
     * @param guideView
     */
    public void removeGuideView(GuideView guideView) {
        mDecorView.removeView(guideView);
    }
}
