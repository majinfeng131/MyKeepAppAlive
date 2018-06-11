package com.lingdian.keepservicealive.mutiprocess;

import android.content.Intent;

/**
 * Created by Administrator on 2016/10/9 0009.
 */

public class RemoteServices extends KeepAliveBaseServices {

    @Override
    Class onMyCreate() {
        return LocalServices.class;
    }

    @Override
    void onMyStartCommand(Intent intent) {

    }

    @Override
    void onMyDestroy() {

    }
}
