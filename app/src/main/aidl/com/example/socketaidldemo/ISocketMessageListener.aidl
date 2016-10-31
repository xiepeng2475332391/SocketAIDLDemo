// ISocketMessageInterface.aidl
package com.example.socketaidldemo;

// Declare any non-default types here with import statements

interface ISocketMessageListener {
    void onReceiveMessage(in String message);
}
