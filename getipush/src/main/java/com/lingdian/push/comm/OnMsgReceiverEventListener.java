package com.lingdian.push.comm;

import android.content.Context;

public interface OnMsgReceiverEventListener {
    public void onReceiveMessageData(Context context, String msg,String msgId);
    public void onReceiveClientId(Context context, String clientid) ;
    public void onBindClientId(Context context, String msg,boolean isSuccess) ;
    public void onUnBindClientId(Context context,String msg,boolean isSuccess) ;
}
