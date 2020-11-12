package com.lee.user_guide.error;

/**
 * @author lee
 * @title: GuideViewEmptyException
 * @description: the guide view did be setting
 * @date 2020/11/12  9:17 下午
 */
public class GuideViewEmptyException extends RuntimeException {
    public GuideViewEmptyException() {
        super("GuideView is empty,please set ui for GuiderView");
    }
}
