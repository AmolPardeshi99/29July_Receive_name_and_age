package com.example.a29july_receivenameage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mBtnShow;
    private TextView mTvName, mTvAge;
    private LocalBroadcastManager broadcastManager;
    private LocalReceiver localReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        broadcastManager = LocalBroadcastManager.getInstance(this);
        mBtnShow = findViewById(R.id.btnShow);
        mTvAge = findViewById(R.id.tvMyAge);
        mTvName = findViewById(R.id.tvMyName);
        registerLocal();
        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.and03.MainActivity");
                intent.putExtra("name","Amol Pardeshi");
                intent.putExtra("Age","22");
                broadcastManager.sendBroadcast(intent);
            }
        });


    }

    private void registerLocal() {
        localReceiver = new LocalReceiver();
        IntentFilter intentFilter = new IntentFilter("com.and03.MainActivity");
        broadcastManager.registerReceiver(localReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        broadcastManager.unregisterReceiver(localReceiver);
    }

    private class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent != null){
                String name  = intent.getStringExtra("name");
                mTvName.setText(name);
                String Age  = intent.getStringExtra("Age");
                mTvAge.setText(Age);
            }
        }
    }
}