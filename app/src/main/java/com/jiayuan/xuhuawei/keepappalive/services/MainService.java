package com.jiayuan.xuhuawei.keepappalive.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.IntDef;
import android.text.TextUtils;
import android.util.Log;

import com.jiayuan.xuhuawei.keepappalive.MyApp;
import com.jiayuan.xuhuawei.keepappalive.constant.ActionConst;
import com.jiayuan.xuhuawei.keepappalive.mutiservice.KeepAliveHelper;

/**
 * 运行的主项目 可以在这里吗 做逻辑业务
 */
public class MainService extends BaseService {

    //https://blog.csdn.net/l2show/article/details/47421961
    public static void startMainService(){
        Context context= MyApp.getAppContext();
        Intent intent=new Intent(ActionConst.ACTION_SET_UP_SERVICE);
        intent.setPackage(context.getPackageName());
//        Intent intent=new Intent(context,MainService.class);
        context.startService(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        KeepAliveHelper.getInstance().startAliveService();
        Log.v("xhw","MainService onStartCommand");
        return  START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
