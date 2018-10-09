package com.lingdian.push.getui;

import android.content.Context;

import com.igexin.sdk.PushManager;
import com.lingdian.push.comm.CommSendMsgHelper;

public class GetuiSendMsgHelper implements CommSendMsgHelper {
    @Override
    public void init(Context context) {
        PushManager.getInstance().initialize(context, GetuiPushService.class);
        PushManager.getInstance().registerPushIntentService(context, GetuiReceiverIntentService.class);
    }

    @Override
    public void bindUserId(Context context,String uid) {
        PushManager.getInstance().bindAlias(context,uid);
    }

    @Override
    public void unBindUserId(Context context,String uid) {
        PushManager.getInstance().unBindAlias(context, uid, false);
    }

    @Override
    public void stop(Context context) {
        PushManager.getInstance().stopService(context);
    }
}
