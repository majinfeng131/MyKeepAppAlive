package com.jiayuan.xuhuawei.keepappalive.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jiayuan.xuhuawei.keepappalive.mutiservice.LocalServices;
import com.jiayuan.xuhuawei.keepappalive.services.MainService;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class KeepAliveReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("xhw","KeepAliveReceiver");
        Intent serviceIntent=new Intent(context, MainService.class);
        context.startService(serviceIntent);
    }
}
