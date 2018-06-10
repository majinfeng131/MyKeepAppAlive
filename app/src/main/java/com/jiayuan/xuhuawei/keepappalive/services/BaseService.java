package com.jiayuan.xuhuawei.keepappalive.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.jiayuan.xuhuawei.keepappalive.mutiservice.KeepAliveHelper;

public class BaseService  extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        KeepAliveHelper.getInstance().initStartAliveService();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        KeepAliveHelper.getInstance().startAliveService();
        return  START_STICKY;
    }
}
