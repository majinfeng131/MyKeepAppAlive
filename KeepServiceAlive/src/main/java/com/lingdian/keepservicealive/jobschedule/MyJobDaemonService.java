package com.lingdian.keepservicealive.jobschedule;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.lingdian.keepservicealive.constant.KSAConst;
import com.lingdian.keepservicealive.utils.KSAUtils;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobDaemonService extends JobService {

    public static  void setupJobService(){
        Context context= KSAConst.getInstance().getAppContext();
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);
        JobInfo jobInfo = new JobInfo.Builder(1, new ComponentName(context.getPackageName(), MyJobDaemonService.class.getName()))
                .setMinimumLatency(KSAConst.INTERVAL_TIME)
                .setOverrideDeadline(KSAConst.INTERVAL_TIME)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setRequiresCharging(true)
                .setRequiresDeviceIdle(true)
                .setPersisted(true)
                .build();
        jobScheduler.schedule(jobInfo);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e("serviceLog", "onStartJob:");

        setupJobService();

        Class clasName= KSAConst.getInstance().getServiceClass();
        boolean isLocalServiceWork = KSAUtils.isServiceWork(this, clasName.getName());
        if(!isLocalServiceWork){
            KSAConst.getInstance().setupMainService();
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        setupJobService();
        return false;
    }
}
