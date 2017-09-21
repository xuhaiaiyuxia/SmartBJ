package com.itheima.smart.beijing.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;



public class NoScollerViewPager extends ViewPager {
    public NoScollerViewPager(Context context) {
        super(context);
    }

    public NoScollerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
