package com.itheima.smart.beijing.page;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima.smart.beijing.R;
import com.itheima.smart.beijing.activity.HomeActivity;

/**
 * Created by Administrator on 2017/9/21.
 */

public class SettingCenterPage extends BasePage {



    public SettingCenterPage(HomeActivity mContext) {
        super(mContext);

    }
    @Override
    public void initData() {
        mIvMenuPic.setVisibility(View.GONE);
        mTvTitleName.setText("设置中心");
        TextView textView = new TextView(mContext);
        textView.setText("设置中心内容");
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        mFlContent.addView(textView);
    }



}
