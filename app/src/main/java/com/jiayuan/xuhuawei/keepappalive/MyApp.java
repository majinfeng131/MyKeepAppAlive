package com.jiayuan.xuhuawei.keepappalive;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.lingdian.keepservicealive.constant.KSAConst;
import com.lingdian.keepservicealive.utils.KSAUtils;

/**
 * Created by Administrator on 2016/10/9 0009.
 */

public class MyApp extends Application {
    private static Application application;
    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        Log.v("xhw","MyApp "+ KSAUtils.getCurProcessName(this));
        KSAConst.getInstance().init(this, MainService.class);
    }

    /**
     * 获取contenxt
     * @return
     */
    public static Context getAppContext(){
        return application.getApplicationContext();
    }

}
