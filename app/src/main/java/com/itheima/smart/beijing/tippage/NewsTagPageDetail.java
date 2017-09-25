package com.itheima.smart.beijing.tippage;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itheima.smart.beijing.R;
import com.itheima.smart.beijing.activity.HomeActivity;
import com.itheima.smart.beijing.pojo.NewsCenterData;
import com.itheima.smart.beijing.pojo.NewsDetailData;
import com.itheima.smart.beijing.utils.DensityUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/24.
 */

public class NewsTagPageDetail {

    protected HomeActivity mContext;
    @Bind(R.id.vp_tpi_page_detail_lunbo)
    ViewPager mVpTpiPageDetailLunbo;
    @Bind(R.id.tv_tpi_page_detail_desc)
    TextView mTvTpiPageDetailDesc;
    @Bind(R.id.ll_tpi_page_detail_points)
    LinearLayout mLlTpiPageDetailPoints;
    @Bind(R.id.lv_tpi_page_detail_newsdata)
    ListView mLvTpiPageDetailNewsdata;
    private View rootView;
    private NewsCenterData.Data.Children mChildren;
    private MyViewPagerAdapter mMyViewPagerAdapter;
    private List<NewsDetailData.Data.TopNews> mTopnews;


    public NewsTagPageDetail(HomeActivity context, NewsCenterData.Data.Children children) {
        mContext = context;
        mChildren = children;
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        getRemoteData();
    }

    private void initPoints() {
        Log.d("NewsTagPageDetail", "mTopnews.size():" + mTopnews);
        //ll_tpi_page_detail_points
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                DensityUtil.dip2px(mContext, 8),
                DensityUtil.dip2px(mContext, 8)
        );
        layoutParams.leftMargin = DensityUtil.dip2px(mContext, 5);
        for (int i = 0; i < mTopnews.size(); i++) {
            ImageView iv = new ImageView(mContext);
            iv.setLayoutParams(layoutParams);
            iv.setBackgroundResource(R.drawable.selectot_point_lunbo);
            iv.setEnabled(Boolean.FALSE);
            mLlTpiPageDetailPoints.addView(iv);
        }
    }

    private void getRemoteData() {

        Log.d("NewsTagPageDetail", "进入getRemoteData");
        String baseUrl = mContext.getString(R.string.base_url);
        String requestUrl = baseUrl + mChildren.url;
        x.http().get(new RequestParams(requestUrl), new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                Log.d("fdsfsf", result);
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void processData(String result) {
        Gson gson = new Gson();
        NewsDetailData newsDetailData = gson.fromJson(result, NewsDetailData.class);
        mTopnews = newsDetailData.data.topnews;
        if (mMyViewPagerAdapter == null) {
            mMyViewPagerAdapter = new MyViewPagerAdapter();
            mVpTpiPageDetailLunbo.setAdapter(mMyViewPagerAdapter);
        } else {
            mMyViewPagerAdapter.notifyDataSetChanged();
        }

        initPoints();

        setLunboInfo(0);
    }

    private void setLunboInfo(int selectedIndex) {
        //tv_tpi_page_detail_desc
        mTvTpiPageDetailDesc.setText(mTopnews.get(selectedIndex).title);
        for (int i = 0; i < mTopnews.size(); i++) {
            mLlTpiPageDetailPoints.getChildAt(i).setEnabled(i == selectedIndex);
        }

    }


    private void initEvent() {
        mVpTpiPageDetailLunbo.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setLunboInfo(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        rootView = View.inflate(mContext, R.layout.tpi_page_detail, null);
        ButterKnife.bind(this, rootView);

    }

    public View getRootView() {
        return rootView;
    }

    private class MyViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            if (mTopnews == null) {
                return 0;
            }
            return mTopnews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            NewsDetailData.Data.TopNews topNews = mTopnews.get(position);
            String topimage = topNews.topimage;
            topimage = topimage.replace("10.0.2.2", "192.168.128.1");
            x.image().bind(imageView, topimage);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
