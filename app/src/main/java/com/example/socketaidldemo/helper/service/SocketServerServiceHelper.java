package com.example.socketaidldemo.helper.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;

import com.example.socketaidldemo.service.SocketServerService;

/**
 * @author : Created by xiepeng
 * @email : xiepeng2015929@gmail.com
 * @created time : 2016/8/5 0005
 * @describe : SocketServerServiceHelper
 */

public class SocketServerServiceHelper extends BaseSocketServiceHelper {

    private static SocketServerServiceHelper sInstance;

    public static SocketServerServiceHelper getInstance() {
        if (sInstance == null) {
            synchronized (SocketServerServiceHelper.class) {
                if (sInstance == null) {
                    sInstance = new SocketServerServiceHelper();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void init(Context context) {
        Intent intent = new Intent(SocketServerService.class.getName());
        intent.setPackage(context.getPackageName());
        context.bindService(intent, connection, Service.BIND_AUTO_CREATE);
    }
}
