package com.jiayuan.xuhuawei.keepappalive.utils;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by Administrator on 2016/10/28 0028.
 */

public class Utils {

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
