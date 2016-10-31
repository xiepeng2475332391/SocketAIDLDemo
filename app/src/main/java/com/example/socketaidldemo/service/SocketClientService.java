package com.example.socketaidldemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.socketaidldemo.service.stub.SocketClientServiceStub;

/**
 * @author : Created by xiepeng
 * @email : xiepeng2015929@gmail.com
 * @created time : 2016/8/5 0005
 * @describe : SocketClientService
 */

public class SocketClientService extends Service {

    private SocketClientServiceStub socketServiceStub;

    @Override
    public IBinder onBind(Intent intent) {
        return socketServiceStub;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        socketServiceStub = new SocketClientServiceStub(getApplication());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        flags = START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }
}
