package com.example.socketaidldemo.service.stub;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;

import com.example.socketaidldemo.ISocketInterface;
import com.example.socketaidldemo.ISocketMessageListener;
import com.example.socketaidldemo.R;
import com.example.socketaidldemo.constant.Constants;
import com.example.socketaidldemo.helper.BaseSocketHelper;
import com.example.socketaidldemo.helper.SocketNotifyHandlerHelper;
import com.example.socketaidldemo.listener.OnReceiveMessageListener;
import com.example.socketaidldemo.utils.LogUtil;
import com.example.socketaidldemo.utils.Utils;

/**
 * @author : Created by xiepeng
 * @email : xiepeng2015929@gmail.com
 * @created time : 2016/10/31 0031
 * @describe : com.example.socketaidldemo.service.stub
 */
public abstract class BaseSocketServiceStub extends ISocketInterface.Stub {

    protected Context context;
    protected BaseSocketHelper helper;
    protected ISocketMessageListener listener;

    public BaseSocketServiceStub(Context context) {
        this.context = context;
    }

    protected OnReceiveMessageListener onReceiveMessageListener = new OnReceiveMessageListener() {
        @Override
        public void onReceiveMessage(String message) {
            LogUtil.e("listener:" + listener + ",message:" + message);
            if(Utils.isBackground(context)){
                String name = getType() == Constants.TYPE_SERVER ? "服务端：" : "客户端：";
                SocketNotifyHandlerHelper.notificationMessage(context, 0, context.getString(R.string.app_name),
                        context.getString(R.string.app_name), name + message, new Intent());
            }
            if(listener != null){
                try {
                    listener.onReceiveMessage(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public void sendMessage(String message) throws RemoteException {
        helper.sendMessage(message);
    }

    @Override
    public void registerMessageListener(ISocketMessageListener listener) throws RemoteException {
        this.listener = listener;
    }

    protected abstract int getType();
}
