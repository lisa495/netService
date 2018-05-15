package com.base.framework.activity;

import android.view.View;

import com.base.framework.presenter.BasePresenter;

/**
 * 接口类--用于链接activity基类
 * {@link ReBaseActivity}
 */
public interface ViewInterface {

    /**
     * 在布局加载之前--用于页面之间传递数据
     */
    void beforeInitView();

    /**
     * 初始化view
     */
    void initContentView(View contentView);

    /**
     * 加载数据(网络请求)，给view设置数据等
     */
    void loadData(BasePresenter presenter);

    /**
     * 设置监听
     */
    void setListener();
}
