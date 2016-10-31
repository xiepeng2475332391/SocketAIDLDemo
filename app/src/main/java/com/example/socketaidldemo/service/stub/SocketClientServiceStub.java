package com.example.socketaidldemo.service.stub;

import android.content.Context;

import com.example.socketaidldemo.constant.Constants;
import com.example.socketaidldemo.helper.SocketClientHelper;

/**
 * @author : Created by xiepeng
 * @email : xiepeng2015929@gmail.com
 * @created time : 2016/10/31 0031
 * @describe : com.example.socketaidldemo.service.stub
 */
public class SocketClientServiceStub extends BaseSocketServiceStub {

    public SocketClientServiceStub(Context context) {
        super(context);
        helper = new SocketClientHelper();
        helper.registerOnReceiveMessageListener(onReceiveMessageListener);
    }

    @Override
    protected int getType() {
        return Constants.TYPE_CLIENT;
    }
}
