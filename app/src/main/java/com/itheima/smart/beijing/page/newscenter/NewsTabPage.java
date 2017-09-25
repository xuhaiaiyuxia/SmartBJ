package com.itheima.smart.beijing.page.newscenter;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.smart.beijing.R;
import com.itheima.smart.beijing.activity.HomeActivity;
import com.itheima.smart.beijing.pojo.NewsCenterData;
import com.itheima.smart.beijing.tippage.NewsTagPageDetail;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class NewsTabPage extends BaseNewsCenterPage {

    @Bind(R.id.tpi_news_center_tab)
    TabPageIndicator mTpiNewsCenterTab;
    @Bind(R.id.vp_new_cnter_viewpager)
    ViewPager mVpNewCnterViewpager;

    private List<NewsCenterData.Data.Children> mChildrenList;

    public NewsTabPage(HomeActivity context, List<NewsCenterData.Data.Children> children) {
        super(context);
        mChildrenList = children;
        initData();
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.newscenter_news, null);
        view.setBackgroundColor(Color.WHITE);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        MyAdapter myAdapter = new MyAdapter();
        mVpNewCnterViewpager.setAdapter(myAdapter);
        mTpiNewsCenterTab.setViewPager(mVpNewCnterViewpager);
    }

    @Override
    public void initEvent() {
        mTpiNewsCenterTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mContext.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                } else {
                    mContext.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mChildrenList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            NewsTagPageDetail newsTagPageDetail = new NewsTagPageDetail(mContext,mChildrenList.get(position));
            View rootView = newsTagPageDetail.getRootView();
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mChildrenList.get(position).title;
        }
    }


}
