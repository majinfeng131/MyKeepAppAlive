package com.jiayuan.xuhuawei.keepappalive;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.jiayuan.xuhuawei.keepappalive.utils.Utils;

/**
 * Created by Administrator on 2016/10/9 0009.
 */

public class MyApp extends Application {
    private static Application application;
    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        Log.v("xhw","MyApp "+ Utils.getCurProcessName(this));
    }

    /**
     * 获取contenxt
     * @return
     */
    public static Context getAppContext(){
        return application.getApplicationContext();
    }

}
