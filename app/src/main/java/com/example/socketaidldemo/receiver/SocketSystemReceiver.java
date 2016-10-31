package com.example.socketaidldemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.socketaidldemo.service.SocketClientService;

/**
 * @author : Created by xiepeng
 * @email : xiepeng2015929@gmail.com
 * @created time : 2016/8/5 0005
 * @describe : SocketSystemReceiver
 */

public class SocketSystemReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(Intent.ACTION_RUN);
        i.setClass(context, SocketClientService.class);
        context.startService(i);
    }
}
