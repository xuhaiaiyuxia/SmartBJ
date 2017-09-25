package com.itheima.smart.beijing.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class NetWorkStatusReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            Toast.makeText(context, "network changed", Toast.LENGTH_LONG).show();
          //  BaseActivity.isNetWorkConnected = NetWorkUtils.getAPNType(context)>0;
        }
    }
}
