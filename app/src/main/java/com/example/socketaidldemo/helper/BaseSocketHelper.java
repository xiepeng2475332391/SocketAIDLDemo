package com.example.socketaidldemo.helper;

import com.example.socketaidldemo.listener.OnReceiveMessageListener;

/**
 * @author : Created by xiepeng
 * @email : xiepeng2015929@gmail.com
 * @created time : 2016/8/5 0005
 * @describe : BaseSocketHelper
 */

public abstract class BaseSocketHelper {

    protected OnReceiveMessageListener onReceiveMessageListener;

    public void registerOnReceiveMessageListener(OnReceiveMessageListener onReceiveMessageListener){
        this.onReceiveMessageListener = onReceiveMessageListener;
    }

    public abstract void sendMessage(String msg);
}
