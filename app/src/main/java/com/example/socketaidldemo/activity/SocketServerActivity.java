package com.example.socketaidldemo.activity;

import android.os.RemoteException;

import com.example.socketaidldemo.constant.Constants;
import com.example.socketaidldemo.helper.service.SocketServerServiceHelper;

/**
 * @author : Created by xiepeng
 * @email : xiepeng2015929@gmail.com
 * @created time : 2016/8/5 0005
 * @describe : SocketServerActivity
 */

public class SocketServerActivity extends BaseSocketActivity {

    @Override
    protected void init() {
        super.init();
        SocketServerServiceHelper.getInstance().init(this);
    }

    @Override
    protected void initListener() {
        super.initListener();
        try {
            SocketServerServiceHelper.getInstance().registerMessageListener(listener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onSend(String message) {
        addMessage(message, false);
        SocketServerServiceHelper.getInstance().sendMessage(message);
    }

    @Override
    protected int getType() {
        return Constants.TYPE_SERVER;
    }
}
