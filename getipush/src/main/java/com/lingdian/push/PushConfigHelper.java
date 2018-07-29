package com.lingdian.push;

import android.content.Context;

import com.lingdian.push.comm.CommSendMsgHelper;
import com.lingdian.push.getui.GetuiSendMsgHelper;

public class PushConfigHelper {
    private CommSendMsgHelper mHelper;
    private static PushConfigHelper instance=null;
    public enum PUSH_TYPE{
        TYPE_GETUI,TYPE_BAIDU
    }
    private PushConfigHelper() {

    }
    public static PushConfigHelper getInstance(){
        if (instance==null){
            instance=new PushConfigHelper();
        }
        return instance;
    }
    public void init(Context context, PUSH_TYPE type){
        if (type==PUSH_TYPE.TYPE_GETUI){
            mHelper=new GetuiSendMsgHelper();
        }else if (type==PUSH_TYPE.TYPE_BAIDU){
            mHelper=new GetuiSendMsgHelper();
        }else{
            mHelper=new GetuiSendMsgHelper();
        }
        mHelper.init(context);
    }
    public void setBindUserId(Context context,String uid){
        mHelper.bindUserId( context,uid);
    }
    public void setUnBindUserId(Context context,String uid){
        mHelper.unBindUserId( context,uid);
    }
    public void stop(Context context){
        mHelper.stop(context);
    }
}
