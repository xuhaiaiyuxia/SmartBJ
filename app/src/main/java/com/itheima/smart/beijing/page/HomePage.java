package com.itheima.smart.beijing.page;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima.smart.beijing.activity.HomeActivity;


public class HomePage extends BasePage {

    public HomePage(HomeActivity mContext) {
        super(mContext);

    }

    @Override
    public void initData() {
        mIvMenuPic.setVisibility(View.GONE);
        mTvTitleName.setText("首页");
        TextView textView = new TextView(mContext);
        textView.setText("首页内容");
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        mFlContent.addView(textView);

    }
}
