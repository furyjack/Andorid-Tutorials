package com.example.admin.weartry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvnum= (TextView) findViewById(R.id.tvnum);
    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            tvnum.setText(intent.getStringExtra("message"));

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        getApplicationContext().registerReceiver(broadcastReceiver, new IntentFilter("com.example.admin.weartry"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        getApplicationContext().unregisterReceiver(broadcastReceiver);
    }
}
