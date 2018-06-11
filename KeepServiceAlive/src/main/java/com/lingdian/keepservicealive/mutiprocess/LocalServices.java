package com.lingdian.keepservicealive.mutiprocess;

import android.content.Context;
import android.content.Intent;

import com.lingdian.keepservicealive.constant.KSAConst;

public class LocalServices extends KeepAliveBaseServices {

    public static void startAlarmService(boolean isBind) {

        AlarmService.startAlarmService();

        Context context= KSAConst.getInstance().getAppContext();

        Intent intent = new Intent(context, LocalServices.class);
        intent.putExtra("bind", isBind);
        context.startService(intent);
    }

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
