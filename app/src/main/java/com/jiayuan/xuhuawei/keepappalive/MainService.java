package com.jiayuan.xuhuawei.keepappalive;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;

import com.jiayuan.xuhuawei.keepappalive.entity.AppEntity;
import com.lingdian.keepservicealive.KSABaseService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 运行的主项目 可以在这里吗 做逻辑业务
 * https://blog.csdn.net/l2show/article/details/47421961
 */
public class MainService extends KSABaseService implements Runnable{

    private static final String PACKAGE_NAME = "com.sand.airdroid";
    private static final long INTERVAL_TIME = 300 ;
    private static final long INTERVAL_MAX_TIME = 2*60*60 ;
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

            long maxValue=index%INTERVAL_MAX_TIME;
            long comValue=index%INTERVAL_TIME;

            boolean isWeekend=AppUtils.getDayofweek()==1||AppUtils.getDayofweek()==7;
            if (isWeekend){

            }


            if (comValue==0&&maxValue!=0){
                excuteSetup(false);
            }else if (maxValue==0){
                saleInteger.getAndSet(1);
                excuteSetup(true);
            }
        }
    }

    private void excuteSetup(boolean isForce){
        if (!isForce){
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
                    Log.v("xhw", "target app is not install");
                }
                Log.v("xhw", "target app is not running");
            } else {
                Log.v("xhw", "target app is  running");
            }
        }else{
            Log.v("xhw", "force setup！");
            if (AppUtils.checkPackInfo(this, PACKAGE_NAME)) {
                PackageManager packageManager = getPackageManager();
                Intent jumpIntent = packageManager.getLaunchIntentForPackage(PACKAGE_NAME);
                startActivity(jumpIntent);
            }else{
                Log.v("xhw", "target app is not install");
            }
        }
    }

}
