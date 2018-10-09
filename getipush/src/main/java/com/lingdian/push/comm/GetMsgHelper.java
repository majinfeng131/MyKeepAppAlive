package com.lingdian.push.comm;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class GetMsgHelper  {
    private static GetMsgHelper instance;
    private List<OnMsgReceiverEventListener> arrayList;
    private GetMsgHelper(){
        arrayList=new ArrayList<>();
    }
    public static GetMsgHelper getInstance(){
        if (instance==null){
            instance=new GetMsgHelper();
        }
        return instance;
    }
    public void addOnMsgReceiverEventListener(OnMsgReceiverEventListener listener){
        if (listener!=null){
            if (!arrayList.contains(listener)){
                arrayList.add(listener);
            }
        }
    }
    public void removeOnMsgReceiverEventListener(OnMsgReceiverEventListener listener){
        if (listener!=null){
            if (arrayList.contains(listener)){
                arrayList.remove(listener);
            }
        }
    }
     void onReceiveMessageData(Context context, String msg, String msgId) {
        for (OnMsgReceiverEventListener listener:arrayList) {
            if (listener!=null){
                listener.onReceiveMessageData(context,msg,msgId);
            }
        }
    }

     void onReceiveClientId(Context context, String clientid) {
        for (OnMsgReceiverEventListener listener:arrayList) {
            if (listener!=null){
                listener.onReceiveClientId(context,clientid);
            }
        }
    }

     void onBindClientId(Context context, String msg, boolean isSuccess) {
        for (OnMsgReceiverEventListener listener:arrayList) {
            if (listener!=null){
                listener.onBindClientId(context,msg,isSuccess);
            }
        }
    }

     void onUnBindClientId(Context context, String msg, boolean isSuccess) {
        for (OnMsgReceiverEventListener listener:arrayList) {
            if (listener!=null){
                listener.onUnBindClientId(context,msg,isSuccess);
            }
        }
    }
}
