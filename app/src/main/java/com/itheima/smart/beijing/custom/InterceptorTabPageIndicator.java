package com.itheima.smart.beijing.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.viewpagerindicator.TabPageIndicator;

/**
 * Created by Administrator on 2017/9/25.
 */

public class InterceptorTabPageIndicator extends TabPageIndicator {
    public InterceptorTabPageIndicator(Context context) {
        super(context);
    }

    public InterceptorTabPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
