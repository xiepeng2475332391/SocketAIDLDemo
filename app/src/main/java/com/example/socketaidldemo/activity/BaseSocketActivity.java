package com.example.socketaidldemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.socketaidldemo.ISocketMessageListener;
import com.example.socketaidldemo.R;
import com.example.socketaidldemo.adapter.SocketMessageAdapter;
import com.example.socketaidldemo.constant.Constants;

/**
 * @author : Created by xiepeng
 * @email : xiepeng2015929@gmail.com
 * @created time : 2016/8/5 0005
 * @describe : BaseSocketActivity
 */

public abstract class BaseSocketActivity extends Activity {

    protected TextView title;
    protected EditText edt_content;
    protected SocketMessageAdapter adapter;
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_socket);
        init();
    }

    /**
     * 初始化
     */
    protected void init(){
        title = (TextView) findViewById(R.id.title);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new SocketMessageAdapter(this);
        listView.setAdapter(adapter);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initListener();
            }
        }, 500);
    }

    /**
     * 初始化监听
     */
    protected void initListener(){
        edt_content = (EditText) findViewById(R.id.edt_content);
        final Button btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setEnabled(false);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
        edt_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(s) || s.toString().trim().length() == 0){
                    btn_ok.setEnabled(false);
                }else {
                    btn_ok.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getAction() == KeyEvent.ACTION_DOWN)) {
                    send();
                }
                return false;
            }
        });
    }

    /**
     * 发送消息
     */
    private void send(){
        String message = edt_content.getText().toString();
        onSend(message);
        edt_content.setText("");
    }

    protected ISocketMessageListener listener = new ISocketMessageListener.Stub(){
        @Override
        public void onReceiveMessage(String message) throws RemoteException {
            Message msg = handler.obtainMessage();
            msg.obj = message;
            handler.sendMessage(msg);
        }
    };

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String message = (String) msg.obj;
            addMessage(message, getType() == Constants.TYPE_SERVER);
        }
    };

    /**
     * 添加消息记录
     * @param message
     */
    protected void addMessage(String message, boolean isClient){
        String type = isClient ? "客户端：" : "服务端：";
        adapter.getList().add(type + message);
        adapter.notifyDataSetChanged();
        listView.setSelection(adapter.getCount() - 1);
    }

    protected abstract void onSend(String message);

    protected abstract int getType();
}
