package com.lingdian.keepservicealive.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lingdian.keepservicealive.constant.KSAConst;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class KeepAliveReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent=new Intent(context, KSAConst.getInstance().getServiceClass());
        context.startService(serviceIntent);
    }
}
