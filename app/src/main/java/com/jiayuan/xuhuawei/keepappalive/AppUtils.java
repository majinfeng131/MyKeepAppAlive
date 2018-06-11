package com.jiayuan.xuhuawei.keepappalive;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Debug;

import com.jaredrummler.android.processes.ProcessManager;
import com.jaredrummler.android.processes.models.AndroidAppProcess;
import com.jiayuan.xuhuawei.keepappalive.entity.AppEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AppUtils {
    private List<ApplicationInfo> appList;

    /**
     * 5.0系统以上获取运行的进程方法
     *
     * @param context
     * @return
     */
    public static List<AppEntity> getAndroidProcess(Context context) {
        List<AppEntity> resule = new ArrayList<AppEntity>();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        PackageManager pm = context.getPackageManager();
        AppUtils proutils = new AppUtils(context);
        List<AndroidAppProcess> listInfo = ProcessManager.getRunningAppProcesses();
        if (listInfo.isEmpty() || listInfo.size() == 0) {
            return null;
        }
        for (AndroidAppProcess info : listInfo) {
            ApplicationInfo app = proutils.getApplicationInfo(info.name);
            // 过滤自己当前的应用
            if (app == null || context.getPackageName().equals(app.packageName)) {
                continue;
            }
            // 过滤系统的应用
            if ((app.flags & app.FLAG_SYSTEM) > 0) {
                continue;
            }
            AppEntity ent = new AppEntity();
            ent.setAppIcon(app.loadIcon(pm));//应用的图标
            ent.setAppName(app.loadLabel(pm).toString());//应用的名称
            ent.setPackageName(app.packageName);//应用的包名
            // 计算应用所占内存大小
            int[] myMempid = new int[]{info.pid};
            Debug.MemoryInfo[] memoryInfo = am.getProcessMemoryInfo(myMempid);
            double memSize = memoryInfo[0].dalvikPrivateDirty / 1024.0;
            int temp = (int) (memSize * 100);
            memSize = temp / 100.0;
            ent.setMemorySize(memSize);//应用所占内存的大小

            resule.add(ent);
        }
        return resule;
    }


    public AppUtils(Context context) {
        // 通过包管理器，检索所有的应用程序
        PackageManager pm = context.getPackageManager();
        appList = pm
                .getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
    }

    /**
     * 通过包名返回一个应用的Application对象
     *
     * @param pkgName
     */
    public ApplicationInfo getApplicationInfo(String pkgName) {
        if (pkgName == null) {
            return null;
        }
        for (ApplicationInfo appinfo : appList) {
            if (pkgName.equals(appinfo.processName)) {
                return appinfo;
            }
        }
        return null;
    }

    public static boolean checkPackInfo(Context context, String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    public static int getDayofweek() {
        Date today = new Date();
        Calendar c=Calendar.getInstance();
        c.setTime(today);
        int weekday=c.get(Calendar.DAY_OF_WEEK);
        return weekday;
    }
}
