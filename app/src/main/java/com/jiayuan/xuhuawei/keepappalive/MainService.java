package com.jiayuan.xuhuawei.keepappalive;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.jiayuan.xuhuawei.keepappalive.entity.AppEntity;
import com.lingdian.keepservicealive.KSABaseService;
import com.lingdian.keepservicealive.constant.KSAConst;
import com.lingdian.keepservicealive.utils.KSAUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 运行的主项目 可以在这里吗 做逻辑业务
 * https://blog.csdn.net/l2show/article/details/47421961
 */
public class MainService extends KSABaseService implements Runnable{

    private static final String PACKAGE_NAME = "com.sand.airdroid";
    private static final long INTERVAL_TIME = 300 ;
    private static AtomicInteger saleInteger=new AtomicInteger(0);
    private Thread thread;
    private boolean isRunning=false;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        onKSACreate();

        isRunning=true;
        thread=new Thread(this);
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        onKSAStartCommand();
        Log.v("xhw", "MainService onStartCommand");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void run() {
        while (isRunning){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int index=saleInteger.getAndIncrement();

            if (index%INTERVAL_TIME==0){
                saleInteger.getAndSet(1);
                excuteSetup();
            }
        }
    }

    private void excuteSetup(){
        List<AppEntity> list = AppUtils.getAndroidProcess(this);
        boolean isRunning = false;
        for (AppEntity bean : list) {
            if (bean.getPackageName().equals(PACKAGE_NAME)) {
                isRunning = true;
                break;
            }
        }
        if (!isRunning) {
            PackageManager packageManager = getPackageManager();
            if (AppUtils.checkPackInfo(this, PACKAGE_NAME)) {
                Intent jumpIntent = packageManager.getLaunchIntentForPackage(PACKAGE_NAME);
                startActivity(jumpIntent);
            } else {
                Log.v("xhw", "com.sand.airdroid is not install");
            }
            Log.v("xhw", "com.sand.airdroid is not running");
        } else {
            Log.v("xhw", "com.sand.airdroid is  running");
        }
    }

}
