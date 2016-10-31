package com.example.socketaidldemo.helper;

import com.example.socketaidldemo.utils.LogUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author : Created by xiepeng
 * @email : xiepeng2015929@gmail.com
 * @created time : 2016/8/5 0005
 * @describe : SocketClientHelper
 */

public class SocketClientHelper extends BaseSocketHelper {

    public static String host = "172.16.1.115";//服务端IP地址
    private static final int PORT = 9999;
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String content = "";

    public SocketClientHelper() {
        new Thread(new ClientRunnable()).start();
    }

    class ClientRunnable implements Runnable {
        @Override
        public void run() {
            try {
                try {
                    LogUtil.e("客户端连接---" + host);
                    socket = new Socket(host, PORT);
                    in = new BufferedReader(new InputStreamReader(socket
                            .getInputStream()));
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                            socket.getOutputStream())), true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                while (true) {
                    if (!socket.isClosed() && socket.isConnected() && !socket.isInputShutdown()) {
                        LogUtil.e("接收消息");
                        if ((content = in.readLine()) != null) {
                            LogUtil.e("content:" + content + onReceiveMessageListener);
                            if(onReceiveMessageListener != null){
                                onReceiveMessageListener.onReceiveMessage(content);
                            }
                        } else {

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送消息
     * @param msg
     */
    public void sendMessage(String msg){
        if(out != null){
            out.println(msg);
        }
    }
}
