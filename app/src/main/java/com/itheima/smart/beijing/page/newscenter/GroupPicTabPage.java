package com.itheima.smart.beijing.page.newscenter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.itheima.smart.beijing.activity.HomeActivity;

/**
 * Created by Administrator on 2017/9/23.
 */

public class GroupPicTabPage extends BaseNewsCenterPage {

    public GroupPicTabPage(HomeActivity context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(mContext);
        textView.setTextColor(Color.BLACK);
        textView.setText("组图");
        return textView;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
