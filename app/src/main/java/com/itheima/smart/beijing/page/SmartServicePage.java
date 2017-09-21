package com.itheima.smart.beijing.page;

import android.view.Gravity;
import android.widget.TextView;

import com.itheima.smart.beijing.activity.HomeActivity;

/**
 * Created by Administrator on 2017/9/21.
 */

public class SmartServicePage extends BasePage {

    public SmartServicePage(HomeActivity mContext) {
        super(mContext);

    }

    @Override
    public void initData() {
        mTvTitleName.setText("智慧服务");
        TextView textView = new TextView(mContext);
        textView.setText("智慧内容");
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        mFlContent.addView(textView);
    }
}
