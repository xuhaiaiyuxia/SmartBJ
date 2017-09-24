package com.itheima.smart.beijing.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.itheima.smart.beijing.R;
import com.itheima.smart.beijing.fragment.LeftFragment;
import com.itheima.smart.beijing.fragment.RightFragment;
import com.itheima.smart.beijing.page.BasePage;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import org.xutils.x;

import butterknife.ButterKnife;

public class HomeActivity extends SlidingFragmentActivity {

    private static final String LEFT_TAG = "LEFT";
    private static final String RIGHT_TAG = "RIGHT_TAG";
    private FragmentManager mFragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.item_sliding_right);
       // x.Ext.init(this.getApplication());
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }



    private void initView() {
        setBehindContentView(R.layout.item_sliding_left);
        // configure the SlidingMenu
        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.LEFT);
        menu.setBehindWidth(300);
        //  menu.setShadowWidthRes(R.dimen.shadow_width);
        //  menu.setShadowDrawable(R.drawable.shadow);

        // 设置滑动菜单视图的宽度
        // menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);

        // menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        // menu.setMenu(R.layout.leftmenu);


    }

    private void initData() {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_left,new LeftFragment(),LEFT_TAG);
        fragmentTransaction.replace(R.id.fl_right,new RightFragment(),RIGHT_TAG);
        fragmentTransaction.commit();
    }

    private void initEvent() {

    }

    public LeftFragment getLeftFragment() {
        LeftFragment leftFragment = (LeftFragment) mFragmentManager.findFragmentByTag(LEFT_TAG);
        return leftFragment;
    }

    public RightFragment getRightFragment() {
        return (RightFragment) mFragmentManager.findFragmentByTag(RIGHT_TAG);
    }

}
