package com.jiayuan.xuhuawei.keepappalive.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jiayuan.xuhuawei.keepappalive.R;

import static com.jiayuan.xuhuawei.keepappalive.mutiservice.AlarmService.NOTICE_ID;

public class CancelNoticeService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("xhw","CancelNoticeService onStartCommand onCreate");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2){
            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            startForeground(NOTICE_ID,builder.build());
            // 开启一条线程，去移除DaemonService弹出的通知
            stopNotificationService();
        }
        return super.onStartCommand(intent, flags, startId);
    }

private void stopNotificationService(){
    new Thread(new Runnable() {
        @Override
        public void run() {
            Log.v("xhw","CancelNoticeService onStartCommand cancel");
            // 延迟1s
            SystemClock.sleep(1000);
            // 取消CancelNoticeService的前台
            stopForeground(true);
            // 移除DaemonService弹出的通知
            NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            manager.cancel(NOTICE_ID);
            // 任务完成，终止自己
            stopSelf();
        }
    }).start();
}

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
