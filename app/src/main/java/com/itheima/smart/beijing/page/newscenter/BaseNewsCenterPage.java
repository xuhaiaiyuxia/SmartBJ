package com.itheima.smart.beijing.page.newscenter;

import android.view.View;

import com.itheima.smart.beijing.activity.HomeActivity;

/**
 * Created by Administrator on 2017/9/23.
 */

public abstract class BaseNewsCenterPage {
    protected HomeActivity mContext;

    private View mRootView;

    public BaseNewsCenterPage(HomeActivity context) {
        mContext = context;
        mRootView = initView();
        initEvent();
    }

    public abstract View initView();
    public abstract void initData();
    public abstract void initEvent();

    public View getRootView() {
        return mRootView;
    }

}
