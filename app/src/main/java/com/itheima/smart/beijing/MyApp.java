package com.itheima.smart.beijing;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2017/9/21.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
