package com.jiayuan.xuhuawei.keepappalive.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Administrator on 2016/11/21 0021.
 */

public class SetUpHelper {
    private IBinder mRemote;
    private Parcel mServiceData;

    public static boolean  startServiceBySelf(Intent intent){
        Class<?> activityManagerNative;
        IBinder mRemote=null;
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
        Parcel  mServiceData = Parcel.obtain();
        mServiceData.writeInterfaceToken("android.app.IActivityManager");
        mServiceData.writeStrongBinder(null);
//          mServiceData.writeStrongBinder(callerBinder);
        intent.writeToParcel(mServiceData, 0);
        mServiceData.writeString(null);
//          mServiceData.writeString(intent.resolveTypeIfNeeded(context.getContentResolver()));
        mServiceData.writeInt(0);


        try {
            if(mRemote == null || mServiceData == null){
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

    public static boolean  startServiceBySelf(ComponentName component){
        Class<?> activityManagerNative;
        IBinder mRemote=null;
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


        Intent intent = new Intent();
        intent.setComponent(component);

        //write pacel
        Parcel  mServiceData = Parcel.obtain();
        mServiceData.writeInterfaceToken("android.app.IActivityManager");
        mServiceData.writeStrongBinder(null);
//          mServiceData.writeStrongBinder(callerBinder);
        intent.writeToParcel(mServiceData, 0);
        mServiceData.writeString(null);
//          mServiceData.writeString(intent.resolveTypeIfNeeded(context.getContentResolver()));
        mServiceData.writeInt(0);


        try {
            if(mRemote == null || mServiceData == null){
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


    public SetUpHelper(Context context, String serviceName){
        initAmsBinder();
        initServiceParcel(context,serviceName);
        startServiceByAmsBinder();
    }

    private void initAmsBinder(){
        Class<?> activityManagerNative;
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
    }


    private void initServiceParcel(Context context, String serviceName){
        Intent intent = new Intent();
        ComponentName component = new ComponentName(context.getPackageName(), serviceName);
        intent.setComponent(component);

        //write pacel
        mServiceData = Parcel.obtain();
        mServiceData.writeInterfaceToken("android.app.IActivityManager");
        mServiceData.writeStrongBinder(null);
//          mServiceData.writeStrongBinder(callerBinder);
        intent.writeToParcel(mServiceData, 0);
        mServiceData.writeString(null);
//          mServiceData.writeString(intent.resolveTypeIfNeeded(context.getContentResolver()));
        mServiceData.writeInt(0);

    }
    private boolean startServiceByAmsBinder(){
        try {
            if(mRemote == null || mServiceData == null){
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
