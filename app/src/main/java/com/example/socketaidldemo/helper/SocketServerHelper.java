package com.example.socketaidldemo.helper;

import com.example.socketaidldemo.utils.LogUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : Created by xiepeng
 * @email : xiepeng2015929@gmail.com
 * @created time : 2016/8/5 0005
 * @describe : SocketServerHelper
 */

public class SocketServerHelper extends BaseSocketHelper {

    private static final int PORT = 9999;
    private List<Socket> mList = new ArrayList<Socket>();
    private ServerSocket server = null;
    private ExecutorService mExecutorService = null; //thread pool

    public SocketServerHelper() {
        new Thread(new ServerRunnable()).start();
    }

    class ServerRunnable implements Runnable {
        @Override
        public void run() {
            try {
                server = new ServerSocket(PORT);
                mExecutorService = Executors.newCachedThreadPool();  //create a thread pool
                LogUtil.e("服务器已启动...");
                Socket client;
                while(true) {
                    client = server.accept();
                    //把客户端放入客户端集合中
                    mList.add(client);
                    mExecutorService.execute(new Service(client)); //start a new thread to handle the connection
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class Service implements Runnable {
        private Socket socket;
        private BufferedReader in = null;
        private String msg = "";

        public Service(Socket socket) {
            this.socket = socket;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //客户端只要一连到服务器，便向客户端发送下面的信息。
                msg = "你好，我是服务端";
                sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            try {
                while(true) {
                    if((msg = in.readLine())!= null) {
                        //当客户端发送的信息为：exit时，关闭连接
                        if(msg.equals("exit")) {
                            System.out.println("ssssssss");
                            mList.remove(socket);
                            in.close();
                            msg = "user:" + socket.getInetAddress()
                                    + "exit total:" + mList.size();
                            socket.close();
                            sendMessage(msg);
                            break;
                            //接收客户端发过来的信息msg，然后发送给客户端。
                        } else {
                            LogUtil.e("receive:" + msg);
                            if(onReceiveMessageListener != null){
                                onReceiveMessageListener.onReceiveMessage(msg);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void sendMessage(String msg) {
        LogUtil.e(msg);
        int num = mList.size();
        for (int index = 0; index < num; index ++) {
            Socket mSocket = mList.get(index);
            PrintWriter pout;
            try {
                pout = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(mSocket.getOutputStream())),true);
                pout.println(msg);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
