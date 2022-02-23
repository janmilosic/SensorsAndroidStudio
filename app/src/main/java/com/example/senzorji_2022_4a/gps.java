package com.example.senzorji_2022_4a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class gps extends AppCompatActivity {

    TextView gp;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        Intent intent = new Intent(getApplicationContext(), GpsTracker.class);
        startService(intent);

        gp = findViewById(R.id.gp);

        //Intent intent = getIntent();
        //gp.setText(intent.getStringExtra("b"));

    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String location = (String) intent.getExtras().get("location");

                gp.setText(location);
            }
        };

        registerReceiver(broadcastReceiver, new IntentFilter("update_location"));

    }
}