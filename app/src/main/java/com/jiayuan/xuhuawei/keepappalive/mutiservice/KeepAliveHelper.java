package com.jiayuan.xuhuawei.keepappalive.mutiservice;

import android.content.Context;

import com.jiayuan.xuhuawei.keepappalive.MyApp;
import com.jiayuan.xuhuawei.keepappalive.constant.ActionConst;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class KeepAliveHelper {
    private static KeepAliveHelper instance = null;
    private Context context;
    private KeepAliveHelper(){
        context= MyApp.getAppContext();
    }

    public static KeepAliveHelper getInstance() {
        if (instance == null) {
            synchronized (KeepAliveHelper.class){
                if (instance == null) {
                    instance = new KeepAliveHelper();
                }
            }
        }
        return instance;
    }

    /**
     * 启动service
     */
    public void startAliveService(){
        AlarmService.startAlarmService();
        LocalServices.startAlarmService(false);
    }
    public void initStartAliveService(){
        AlarmService.startAlarmService();
        LocalServices.startAlarmService(true);
    }
}
