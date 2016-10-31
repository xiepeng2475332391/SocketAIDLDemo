package com.example.socketaidldemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.socketaidldemo.R;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_client = (Button) findViewById(R.id.btn_client);
        Button btn_server = (Button) findViewById(R.id.btn_server);
        btn_client.setOnClickListener(this);
        btn_server.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_client:
                startActivity(new Intent(this, SocketClientActivity.class));
                break;
            case R.id.btn_server:
                startActivity(new Intent(this, SocketServerActivity.class));
                break;
        }
    }
}
