package com.lingdian.push.comm;

import android.content.Context;
import android.util.Log;

/**
 * 接受消息的帮助类
 */
public class CommReceiveMsgHeplerListener implements OnMsgReceiverEventListener {

    @Override
    public void onReceiveMessageData(Context context, String msg, String msgId) {
        Log.v("xuhuawei","msg"+msg);
        GetMsgHelper.getInstance().onReceiveMessageData(context, msg, msgId);

    }
    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.v("xuhuawei","clientid"+clientid);
        GetMsgHelper.getInstance().onReceiveClientId(context,clientid);
    }
    @Override
    public void onBindClientId(Context context, String msg, boolean isSuccess) {
        GetMsgHelper.getInstance().onBindClientId(context,msg,isSuccess);
    }
    @Override
    public void onUnBindClientId(Context context, String msg, boolean isSuccess) {
        GetMsgHelper.getInstance().onUnBindClientId(context,msg,isSuccess);
    }
}
