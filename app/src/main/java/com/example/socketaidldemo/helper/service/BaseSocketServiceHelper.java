package com.example.socketaidldemo.helper.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.socketaidldemo.ISocketInterface;
import com.example.socketaidldemo.ISocketMessageListener;
import com.example.socketaidldemo.utils.LogUtil;

/**
 * @author : Created by xiepeng
 * @email : xiepeng2015929@gmail.com
 * @created time : 2016/8/5 0005
 * @describe : BaseSocketServiceHelper
 */

public abstract class BaseSocketServiceHelper implements ISocketInterface {

    protected ISocketInterface socketInterface;

    protected ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            socketInterface = ISocketInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public IBinder asBinder() {
        return null;
    }

    @Override
    public void sendMessage(String message) {
        try {
            LogUtil.e("sendMessage----->" + message + ",interface:" + socketInterface);
            if(socketInterface != null){
                socketInterface.sendMessage(message);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    @Override
    public void registerMessageListener(ISocketMessageListener listener) throws RemoteException {
        try {
            LogUtil.e("registerMessageListener------->" + listener + ",.." + socketInterface);
            if(socketInterface != null){
                socketInterface.registerMessageListener(listener);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    public abstract void init(Context context);

}
