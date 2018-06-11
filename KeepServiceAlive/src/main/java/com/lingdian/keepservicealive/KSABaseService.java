package com.lingdian.keepservicealive;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lingdian.keepservicealive.mutiprocess.KeepAliveHelper;


public abstract class KSABaseService extends Service{
    /**
     * 在onCreate中执行
     */
    public static void onKSACreate() {
        KeepAliveHelper.getInstance().initStartAliveService();
    }
    /**
     * 在onCommand 执行
     */
    public static void onKSAStartCommand(){
        KeepAliveHelper.getInstance().startAliveService();
    }
}
