package com.itheima.smart.beijing.page;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itheima.smart.beijing.R;
import com.itheima.smart.beijing.activity.HomeActivity;
import com.itheima.smart.beijing.fragment.LeftFragment;
import com.itheima.smart.beijing.page.newscenter.BaseNewsCenterPage;
import com.itheima.smart.beijing.page.newscenter.GroupPicTabPage;
import com.itheima.smart.beijing.page.newscenter.InteractiveTabPage;
import com.itheima.smart.beijing.page.newscenter.NewsTabPage;
import com.itheima.smart.beijing.page.newscenter.TopicTabPage;
import com.itheima.smart.beijing.pojo.NewsCenterData;
import com.itheima.smart.beijing.utils.MyConstaints;
import com.itheima.smart.beijing.utils.NetWorkUtils;
import com.itheima.smart.beijing.utils.SPUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class NewsCenterPage extends BasePage {

    private List<BaseNewsCenterPage> mBaseNewsCenterPages = new ArrayList<>();
    private NewsCenterData mNewsCenterData;

    public NewsCenterPage(HomeActivity mContext) {
        super(mContext);

    }

    @Override
    public void selectPage(Integer index) {
        Log.d("NewsCenterPage", "显示页面的索引:"+index);
    }

    @Override
    public void initData() {

        mTvTitleName.setText("新闻中心");
        TextView textView = new TextView(mContext);
        textView.setText("新闻中心内容");
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        mFlContent.addView(textView);


        int apnType = NetWorkUtils.getAPNType(mContext);
        Log.d("NewsTagPageDetail", "apnType:" + apnType);
        if (apnType != 0) {
            getRemoteData();
        } else {
            String datas = SPUtils.getString(mContext, MyConstaints.NEWS_DATA_LOCAL_KEY, null);
            if (!TextUtils.isEmpty(datas)) {
                renderLeftMenu(datas);
            }
        }

        // getRemoteData();

    }

    public void setOnGetRemoteDataSuccessListener(OnGetRemoteDataSuccessListener onGetRemoteDataSuccessListener) {
        mOnGetRemoteDataSuccessListener = onGetRemoteDataSuccessListener;
    }

    private OnGetRemoteDataSuccessListener mOnGetRemoteDataSuccessListener;



    public interface OnGetRemoteDataSuccessListener {
        void onSuccess( List<NewsCenterData.Data> list);
    }

    private void getRemoteData() {

        RequestParams entity = new RequestParams(mContext.getString(R.string.INDEX_CATAGORY_URL));
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                SPUtils.putString(mContext, MyConstaints.NEWS_DATA_LOCAL_KEY,result);
                renderLeftMenu(result);

            /*    if (mOnGetRemoteDataSuccessListener != null) {
                    List<NewsCenterData.Data> data =  newsCenterData.data;
                    mOnGetRemoteDataSuccessListener.onSuccess(data);
                }*/

            }

            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("onError");
                Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
            }

            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
                System.out.println("onCancelled");
            }

            @Override
            public void onFinished() {
                System.out.println("onFinished");
            }
        });
    }

    private void renderLeftMenu(String result) {
        //解析resultSystem.out.println("onError");
        Log.d("NewsCenterPage", result);
        Gson gson = new Gson();
        mNewsCenterData = gson.fromJson(result, NewsCenterData.class);
        Log.d("NewsCenterPage", mNewsCenterData.data.get(1).title);

        LeftFragment leftFragment = mContext.getLeftFragment();
        Log.d("NewsCenterPage", "leftFragment:" + leftFragment);
        leftFragment.setMenuData(mNewsCenterData.data);
        leftFragment.setOnLeftMenuSelectedListener(new LeftFragment.OnLeftMenuSelectedListener() {
            @Override
            public void selected(Integer index) {
                selectPage(index);
                viewDatas(index);
            }
        });

        initNewPage(mNewsCenterData);
        //显示数据
        viewDatas(0);

    }

    private void viewDatas(int i) {
        mTvTitleName.setText(mNewsCenterData.data.get(i).title);
        mFlContent.removeAllViews();
        mFlContent.addView(mBaseNewsCenterPages.get(i).getRootView());
    }

    private void initNewPage(NewsCenterData newsCenterData) {
        //mBaseNewsCenterPages
        for (NewsCenterData.Data data : newsCenterData.data) {
           // BaseNewsCenterPage news = new NewsTabPage(mContext);
            switch (data.type) {
                case "1":
                    mBaseNewsCenterPages.add(new NewsTabPage(mContext,mNewsCenterData.data.get(0).children));
                    break;
                case "10":
                    mBaseNewsCenterPages.add(new TopicTabPage(mContext));
                    break;
                case "2":
                    mBaseNewsCenterPages.add(new GroupPicTabPage(mContext));
                    break;
                case "3":
                    mBaseNewsCenterPages.add(new InteractiveTabPage(mContext));
                    break;
            }
        }
    }
}
