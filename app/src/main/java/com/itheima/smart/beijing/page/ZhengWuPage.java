package com.itheima.smart.beijing.page;

import android.view.Gravity;
import android.widget.TextView;

import com.itheima.smart.beijing.activity.HomeActivity;


public class ZhengWuPage extends BasePage {

    public ZhengWuPage(HomeActivity mContext) {
        super(mContext);
    }

    @Override
    public void initData() {
        mTvTitleName.setText("政务中心");
        TextView textView = new TextView(mContext);
        textView.setText("政府内容");
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        mFlContent.addView(textView);
    }
}
