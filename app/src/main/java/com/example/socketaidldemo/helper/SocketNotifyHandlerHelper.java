package com.example.socketaidldemo.helper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.socketaidldemo.R;

/**
 * @author : Created by xiepeng
 * @email : xiepeng2015929@gmail.com
 * @created time : 2016/8/9 0009
 * @describe : SocketNotifyHandlerHelper
 */

public class SocketNotifyHandlerHelper {

    /**
     * 通知栏显示消息
     * @param context
     * @param id
     * @param ticker
     * @param contenttitle
     * @param content
     * @param intent
     */
    public static void notificationMessage(Context context, long id, CharSequence ticker, CharSequence contenttitle, CharSequence content, Intent intent){
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        builder.setContentTitle(contenttitle);
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setTicker(ticker);
        Notification notification = builder.getNotification();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        manager.notify((int) id,notification);
    }
}
