package com.lee.user_guide.listener;

import com.lee.user_guide.core.Controller;

/**
 * @author lee
 * @title: OnGuideChangeListener
 * @description: 监听引导层的 显示与隐藏
 * @date 2020/11/12  5:40 下午
 */
public interface OnGuideChangeListener {
    /**
     * 当引导层显示后被调起
     */
    void onShowed(Controller controller);

    /**
     * 当引导层移除后被调起
     */
    void onRemoved(Controller controller);
}
