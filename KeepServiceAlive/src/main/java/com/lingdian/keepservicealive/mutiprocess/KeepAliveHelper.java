package com.lingdian.keepservicealive.mutiprocess;


/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class KeepAliveHelper {
    private static KeepAliveHelper instance = null;

    private KeepAliveHelper(){

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
        LocalServices.startAlarmService(false);
    }
    public void initStartAliveService(){
        LocalServices.startAlarmService(true);
    }
}
