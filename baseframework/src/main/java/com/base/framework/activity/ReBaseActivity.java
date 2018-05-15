package com.base.framework.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.base.framework.R;
import com.base.framework.presenter.BasePresenter;
import com.base.framework.utils.ActivityManager;
import com.base.framework.viewWiget.common.TopBarView;

/**
 *  activity的基类
 */

public abstract class ReBaseActivity<T extends BasePresenter> extends FragmentActivity implements ViewInterface{

//    protected T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeInitView();
        T mPresenter=createPresenter();
        // 加载view布局
        setCommContentView(createHeaderView(),getContentResID());
        loadData(mPresenter);
        setListener();
        addStack();

    }

    @Override
    protected void onStop() {
        super.onStop();
//        AntiHijackingUtil.checkActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeStack();
    }

    private LinearLayout createContainer(){
        LayoutInflater inflater = getLayoutInflater();
        return (LinearLayout) inflater.inflate(R.layout.activity_base_container,null);
    }
    private void setCommContentView(@LayoutRes int headerResID, @LayoutRes int contentResID){
        LinearLayout container=createContainer();
        View header;
        if(headerResID<=0){
            //TODO 使用默认的值
            header=new TopBarView(this);
            initTopBarView((TopBarView)header);
        }else {
            header = getLayoutInflater().inflate(headerResID,container,false);
            initHeaderView(header);
        }
        if(contentResID<=0){
            throw new RuntimeException("the value of contentResID must not below 0");
        }
        View contentView = getLayoutInflater().inflate(contentResID,container,false);
        initContentView(contentView);
        container.addView(header);
        container.addView(contentView);
        super.setContentView(container);
    }
    private void addStack(){
        ActivityManager.getInstance().addActivity(this);
    }
    private void removeStack(){
        ActivityManager.getInstance().removeActivity(this);
    }

    protected int createHeaderView(){
        return 0;
    }
    protected void initHeaderView(View headerView){}

    protected abstract T createPresenter();
    protected abstract void initTopBarView(TopBarView topBarView);
    protected abstract int getContentResID();

}
