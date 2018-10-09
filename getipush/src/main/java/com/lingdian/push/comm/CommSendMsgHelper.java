package com.lingdian.push.comm;

import android.content.Context;

public interface CommSendMsgHelper {
    public void init(Context context);
    public void bindUserId(Context context,String uid);
    public void unBindUserId(Context context,String uid);
    public void stop(Context context);
}
