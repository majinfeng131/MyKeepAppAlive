package com.jiayuan.xuhuawei.keepappalive.mutiservice;

import android.content.Context;
import android.content.Intent;

import com.jiayuan.xuhuawei.keepappalive.MyApp;

public class LocalServices extends KeepAliveBaseServices {

    public static void startAlarmService(boolean isBind) {
        Context context = MyApp.getAppContext();
        Intent intent = new Intent(context, LocalServices.class);
        intent.putExtra("bind", isBind);
        context.startService(intent);
    }

    private static final String TAG = "OldLocalServices";

    @Override
    Class onMyCreate() {
        return RemoteServices.class;
    }

    @Override
    void onMyStartCommand(Intent intent) {

    }

    @Override
    void onMyDestroy() {

    }
}
