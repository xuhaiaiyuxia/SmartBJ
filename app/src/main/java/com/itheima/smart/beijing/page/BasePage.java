package com.itheima.smart.beijing.page;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.smart.beijing.R;
import com.itheima.smart.beijing.activity.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class BasePage {
    protected HomeActivity mContext;
    @Bind(R.id.iv_menu_pic)
    protected ImageView mIvMenuPic;
    @Bind(R.id.tv_title_name)
    protected TextView mTvTitleName;
    @Bind(R.id.fl_content)
    protected FrameLayout mFlContent;
    private View mViewRoot;

    BasePage(HomeActivity mContext) {
        this.mContext = mContext;
        initView();
        initEvent();

    }

    private void initEvent() {
        mIvMenuPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.getSlidingMenu().toggle();
            }
        });
    }

    public void selectPage(Integer index) {

    }

    private void initView() {
        mViewRoot = View.inflate(mContext, R.layout.basepage_view, null);
        ButterKnife.bind(this,mViewRoot);
    }

    public View getViewRoot() {
        return mViewRoot;
    }

    public abstract void initData();





}
