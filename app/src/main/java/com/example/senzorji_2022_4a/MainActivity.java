package com.example.senzorji_2022_4a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SensorManager sensorManager;
    TextView senzorji;
    Button pospesek, gps, map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        senzorji = findViewById(R.id.sensorList);

        pospesek = findViewById(R.id.pospesek);
        gps = findViewById(R.id.gps);
        map = findViewById(R.id.map);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for(int i =0; i<sensorList.size(); i++){
            senzorji.append(i + ".Ime: " + sensorList.get(i).getName() + " Tip: " + sensorList.get(i).getType() + "\n");
        }

        pospesek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),pospesek.class);
                String s = "Sporočilo iz senzorjev";
                intent.putExtra("a",s);
                startActivity(intent);
            }
        });

        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),gps.class);
                String s = "Sporočilo iz gps";
                intent.putExtra("b",s);
                startActivity(intent);
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),maps.class);
                String s = "Sporočilo iz maps";
                intent.putExtra("c",s);
                startActivity(intent);
            }
        });

    }
}