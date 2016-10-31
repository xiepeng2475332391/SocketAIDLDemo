// IServerSocketInterface.aidl
package com.example.socketaidldemo;
import com.example.socketaidldemo.ISocketMessageListener;

interface ISocketInterface {
    void sendMessage(String message);
    void registerMessageListener(in ISocketMessageListener listener);
}
