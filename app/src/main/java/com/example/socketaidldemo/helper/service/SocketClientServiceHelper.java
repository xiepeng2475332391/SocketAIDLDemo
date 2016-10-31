package com.example.socketaidldemo.helper.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;

import com.example.socketaidldemo.service.SocketClientService;
import com.example.socketaidldemo.utils.LogUtil;

/**
 * @author : Created by xiepeng
 * @email : xiepeng2015929@gmail.com
 * @created time : 2016/8/5 0005
 * @describe : SocketServerServiceHelper
 */

public class SocketClientServiceHelper extends BaseSocketServiceHelper {

    private static SocketClientServiceHelper sInstance;

    public static SocketClientServiceHelper getInstance() {
        if (sInstance == null) {
            synchronized (SocketClientServiceHelper.class) {
                if (sInstance == null) {
                    sInstance = new SocketClientServiceHelper();
                }
            }
        }
        LogUtil.e("instance------->" + sInstance);
        return sInstance;
    }

    @Override
    public void init(Context context) {
        Intent intent = new Intent(SocketClientService.class.getName());
        intent.setPackage(context.getPackageName());
        context.bindService(intent, connection, Service.BIND_AUTO_CREATE);
    }
}
