package com.lee.user_guide.error;

/**
 * @author lee
 * @title: NoGuidePageException
 * @description: 没有设置用户引导界面
 * @date 2020/11/12  7:54 下午
 */
public class NoGuidePageException extends RuntimeException {
    public NoGuidePageException() {
        super("没有设置用户引导界面");
    }
}
