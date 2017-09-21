package com.itheima.smart.beijing.page;

import android.view.Gravity;
import android.widget.TextView;

import com.itheima.smart.beijing.activity.HomeActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/9/21.
 */

public class NewsCenterPage extends BasePage {

    public NewsCenterPage(HomeActivity mContext) {
        super(mContext);

    }

    @Override
    public void initData() {

        mTvTitleName.setText("新闻中心");
        TextView textView = new TextView(mContext);
        textView.setText("新闻中心内容");
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        mFlContent.addView(textView);

        getRemoteData();
    }

    private void getRemoteData() {
        RequestParams entity = new RequestParams("http://192.168.128.1/lastVersionInfo");
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析resultSystem.out.println("onError");

            }

            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("onError");
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
}
