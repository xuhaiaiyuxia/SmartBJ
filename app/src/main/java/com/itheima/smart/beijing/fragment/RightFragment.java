package com.itheima.smart.beijing.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.itheima.smart.beijing.R;
import com.itheima.smart.beijing.page.BasePage;
import com.itheima.smart.beijing.page.HomePage;
import com.itheima.smart.beijing.page.NewsCenterPage;
import com.itheima.smart.beijing.page.SettingCenterPage;
import com.itheima.smart.beijing.page.SmartServicePage;
import com.itheima.smart.beijing.page.ZhengWuPage;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/20.
 */

public class RightFragment extends BaseFragment {


    @Bind(R.id.vp_contents)
    ViewPager mVpContents;
    @Bind(R.id.rb_home_first)
    RadioButton mRbHomeFirst;
    @Bind(R.id.rb_news)
    RadioButton mRbNews;
    @Bind(R.id.rb_smart_service)
    RadioButton mRbSmartService;
    @Bind(R.id.rb_govaffairs)
    RadioButton mRbGovaffairs;
    @Bind(R.id.rb_setting)
    RadioButton mRbSetting;
    @Bind(R.id.rg_btns)
    RadioGroup mRgBtns;


    private List<BasePage> mViews = new ArrayList<BasePage>();
    private MyAdapter mMyAdapter;

    @Override
    protected View initView() {
        View v = View.inflate(mActivity, R.layout.activity_home, null);

        return v;
    }

    @Override
    protected void initData() {
        mRgBtns.check(R.id.rb_home_first);

        mViews.add(new HomePage(mActivity));
        mViews.add(new NewsCenterPage(mActivity));
        mViews.add(new SmartServicePage(mActivity));
        mViews.add(new ZhengWuPage(mActivity));
        mViews.add(new SettingCenterPage(mActivity));

        mMyAdapter = new MyAdapter();
        mVpContents.setAdapter(mMyAdapter);
        swichPage();
    }

    public BasePage getSelectPage() {

       return mViews.get(selectIndex);
    }



    @Override
    protected void initEvent() {



        mRgBtns.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home_first:

                        selectIndex = 0;
                        break;
                    case R.id.rb_news:
                        selectIndex = 1;
                        break;
                    case R.id.rb_smart_service:
                        selectIndex = 2;
                        Log.d("RightFragment", "选择了智慧服务");
                        break;
                    case R.id.rb_govaffairs:
                        selectIndex = 3;
                        break;
                    case R.id.rb_setting:
                        selectIndex = 4;
                        break;

                }
                swichPage();
            }
        });

    }

    private void swichPage() {
        Log.d("RightFragment", "method execute");
        if (selectIndex == 0 || selectIndex == 4) {
            mActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        } else {
            mActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.LEFT);
        }
        mVpContents.setCurrentItem(selectIndex);
    }

    private int selectIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePage page = mViews.get(position);
            View viewRoot = page.getViewRoot();
            page.initData();
            container.addView(viewRoot);
            return viewRoot;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


}
