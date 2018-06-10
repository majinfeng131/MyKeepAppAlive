package com.jiayuan.xuhuawei.keepappalive.mutiservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import android.util.Log;

import com.jiayuan.xuhuawei.keepappalive.aidls.MutiProcessService;
import com.jiayuan.xuhuawei.keepappalive.services.MainService;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static com.jiayuan.xuhuawei.keepappalive.constant.ActionConst.ACTION_SET_UP_SERVICE;

/**
 * Created by Administrator on 2016/10/28 0028.
 */

public abstract class KeepAliveBaseServices extends Service {
    private KeepAliveBaseServices.MyBinder myBinder;
    private KeepAliveBaseServices.MyConn myConn;
    private Class otherServiceClass;

    protected String TAG = "";

    abstract Class onMyCreate();

    abstract void onMyStartCommand(Intent intent);

    abstract void onMyDestroy();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        TAG = this.getClass().getName();
        Log.e("xhw", "onCreate:" + TAG);
        otherServiceClass = onMyCreate();
        myBinder = new KeepAliveBaseServices.MyBinder();
        myConn = new KeepAliveBaseServices.MyConn();


    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onMyStartCommand(intent);

        Log.e("xhw", "onStartCommand: " + TAG);
        boolean bind = intent.getBooleanExtra("bind", false);
        if (bind) {
            Intent otheriIntent = new Intent(this, otherServiceClass);
            startService(otheriIntent);
        }
        bindService(new Intent(this, otherServiceClass), myConn, Context.BIND_IMPORTANT);

        if (bind) {
            MainService.startMainService();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onMyDestroy();
        Log.e("xhw", "onDestroy: " + TAG);
    }

    class MyBinder extends MutiProcessService.Stub {
        @Override
        public String getServiceName() throws RemoteException {
            return KeepAliveBaseServices.this.getPackageName();
        }
    }

    /**
     * 连接
     */
    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.v("xhw", "onServiceConnected:" + TAG);
//            KeepAliveBaseServices.MyBinder binder=(KeepAliveBaseServices.MyBinder)iBinder;
            MutiProcessService binder=  MutiProcessService.Stub.asInterface(iBinder);
            try {
                binder.getServiceName();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {//异常连接断开的时候 会回调  正常解绑的时候 不会回调
            Log.e("xhw", "onServiceDisconnected:" + TAG);
            Intent intent = new Intent();
            intent.putExtra("bind", true);
            intent.setComponent(componentName);
            startServiceBySelf(intent);
        }
    }

    public static boolean startServiceBySelf(Intent intent) {
        Class<?> activityManagerNative;
        IBinder mRemote = null;
        try {
            activityManagerNative = Class.forName("android.app.ActivityManagerNative");
            Object amn = activityManagerNative.getMethod("getDefault").invoke(activityManagerNative);
            Field mRemoteField = amn.getClass().getDeclaredField("mRemote");
            mRemoteField.setAccessible(true);
            mRemote = (IBinder) mRemoteField.get(amn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        //write pacel
        Parcel mServiceData = Parcel.obtain();
        mServiceData.writeInterfaceToken("android.app.IActivityManager");
        mServiceData.writeStrongBinder(null);
//          mServiceData.writeStrongBinder(callerBinder);
        intent.writeToParcel(mServiceData, 0);
        mServiceData.writeString(null);
//          mServiceData.writeString(intent.resolveTypeIfNeeded(context.getContentResolver()));
        mServiceData.writeInt(0);


        try {
            if (mRemote == null || mServiceData == null) {
                Log.e("Daemon", "REMOTE IS NULL or PARCEL IS NULL !!!");
                return false;
            }
            mRemote.transact(34, mServiceData, null, 0);//START_SERVICE_TRANSACTION = 34
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }

    }

}
