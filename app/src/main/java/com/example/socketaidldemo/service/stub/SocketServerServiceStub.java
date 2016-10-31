package com.example.socketaidldemo.service.stub;

import android.content.Context;

import com.example.socketaidldemo.constant.Constants;
import com.example.socketaidldemo.helper.SocketServerHelper;

/**
 * @author : Created by xiepeng
 * @email : xiepeng2015929@gmail.com
 * @created time : 2016/10/31 0031
 * @describe : com.example.socketaidldemo.service.stub
 */
public class SocketServerServiceStub extends BaseSocketServiceStub {

    public SocketServerServiceStub(Context context) {
        super(context);
        helper = new SocketServerHelper();
        helper.registerOnReceiveMessageListener(onReceiveMessageListener);
    }

    @Override
    protected int getType() {
        return Constants.TYPE_SERVER;
    }
}
