package com.itheima.smart.beijing.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itheima.smart.beijing.R;
import com.itheima.smart.beijing.utils.DensityUtil;
import com.itheima.smart.beijing.utils.MyConstaints;
import com.itheima.smart.beijing.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuidActivity extends Activity {

    @Bind(R.id.vp_guid_setting)
    ViewPager mVpGuidSetting;
    @Bind(R.id.ll_guid_gray)
    LinearLayout mLlGuidGray;
    @Bind(R.id.v_guid_red)
    View mVGuidRed;
    @Bind(R.id.btn_guid_start_tiyan)
    Button mBtnGuidStartTiyan;


    private int[] imageIdArr = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};

    private List<ImageView> mImageViewList = new ArrayList<ImageView>();
    private MyAdapter mPageViewAdapter;
    private int mPostitionOffSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initView() {

    }

    private void initData() {
        for (int i = 0; i < imageIdArr.length; i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(imageIdArr[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mImageViewList.add(imageView);

            //mLlGuidGray
            View view = new View(this);
            int x = DensityUtil.dip2px(this, 10);

            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(x, x);
            if (i != 0) {
                layoutParams.leftMargin = x;
            }
            view.setBackgroundResource(R.drawable.v_point_gray);
            view.setLayoutParams(layoutParams);
            mLlGuidGray.addView(view);

        }
        mPageViewAdapter = new MyAdapter();
        mVpGuidSetting.setAdapter(mPageViewAdapter);
    }

    private void initEvent() {
        mLlGuidGray.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
//                //ll_guid_gray
                mPostitionOffSet = mLlGuidGray.getChildAt(1).getLeft() - mLlGuidGray.getChildAt(0).getLeft();
                mLlGuidGray.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // System.out.println(mPostitionOffSet);
            }
        });

        mVpGuidSetting.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("GuidActivity", "positionOffset:" + positionOffset);
                FrameLayout.LayoutParams layoutParams =
                        (FrameLayout.LayoutParams) mVGuidRed.getLayoutParams();
                layoutParams.leftMargin = Math.round((position + positionOffset) * mPostitionOffSet);
                mVGuidRed.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == imageIdArr.length - 1) {
                    mBtnGuidStartTiyan.setVisibility(View.VISIBLE);
                } else {
                    mBtnGuidStartTiyan.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @OnClick(R.id.btn_guid_start_tiyan)
    public void onViewClicked() {
        SPUtils.putBoolean(this, MyConstaints.ISSETUPFINISH,true);
        startActivity(new Intent(this,HomeActivity.class));
        finish();
    }

    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = mImageViewList.get(position);

            container.addView(v);

            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
