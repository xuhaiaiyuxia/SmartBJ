package com.itheima.smart.beijing.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2017/9/20.
 */

public class LeftFragment extends BaseFragment {
    @Override
    protected View initView() {
        TextView textView = new TextView(mActivity);
        textView.setText("left");
        textView.setTextSize(30);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }
}
