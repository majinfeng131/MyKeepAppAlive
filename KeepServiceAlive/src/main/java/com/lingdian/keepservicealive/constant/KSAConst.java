package com.lingdian.keepservicealive.constant;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class KSAConst {
    public static final String ALARM_ACTION="alarmService";
    public static final String LOCAL_SERVICE_ACTION="localService";
    public static final String REMOTE_SERVICE_ACTION="remoteService";
    public static final String MAIN_SERVICE_ACTION="mainService";


    public static final int INTERVAL_TIME=10 * 1000;


    private Class<? extends Service> className;
    private Application application;

    private static KSAConst instance=null;
    private KSAConst(){

    }
    public static KSAConst getInstance(){
        if (instance==null){
            synchronized (KSAConst.class){
                if (instance==null){
                    instance=new KSAConst();
                }
            }
        }
        return instance;
    }
    public void init(Application application, Class<? extends Service> className){
        this.application=application;
        this.className=className;
    }
    public Context getAppContext(){
        return application.getApplicationContext();
    }
    public Class<? extends Service> getServiceClass(){
        return className;
    }

    /**
     * 启动要保活的Service
     *
     */
    public void setupMainService(){
        Context context= KSAConst.getInstance().getAppContext();
        Class className= KSAConst.getInstance().getServiceClass();
        Intent mianIntent=new Intent(context,className);
        context.startService(mianIntent);
    }
}
