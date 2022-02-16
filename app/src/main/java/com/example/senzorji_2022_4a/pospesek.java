package com.example.senzorji_2022_4a;

import static java.lang.Math.sqrt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class pospesek extends AppCompatActivity  implements SensorEventListener {

    //TextView tv;
    
    private SensorManager sensorManager;
    private Sensor msensor;
    private float[] nGravity = new float[3];
    private float[] mGravity;
    private double mSkupni;
    private double maxP = 0;
    private float sBarTest = 0;

    TextView txtX, txtY, txtZ, txtSkupni, txtMP;
    SeekBar sBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pospesek);

        /*
        tv = findViewById(R.id.tv);
        
        Intent intent = getIntent();
        tv.setText(intent.getStringExtra("a"));
         */

        txtX = findViewById(R.id.txtX);
        txtY = findViewById(R.id.txtY);
        txtZ = findViewById(R.id.txtZ);
        txtSkupni = findViewById(R.id.txtSkupni);
        txtMP = findViewById(R.id.txtMP);

        sBar = findViewById(R.id.sBar);

        
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        msensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,msensor,SensorManager.SENSOR_DELAY_NORMAL);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        final float filter = 0.8f;

        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            mGravity = event.values;

            nGravity[0] = filter * nGravity[0]+(1-filter) * mGravity[0];
            nGravity[1] = filter * nGravity[1]+(1-filter) * mGravity[1];
            nGravity[2] = filter * nGravity[2]+(1-filter) * mGravity[2];

            float x = mGravity[0] - nGravity[0];
            float y = mGravity[1] - nGravity[1];
            float z = mGravity[2] - nGravity[2];

            mSkupni = sqrt(x * x + y * y + z * z);

            if (mSkupni > maxP){
                maxP = mSkupni;
            }

            txtX.setText("X: " + String.format("%4fm", x));
            txtY.setText("Y: " + String.format("%4fm",y));
            txtZ.setText("Z: " + String.format("%4fm",z));
            txtSkupni.setText("SKUPAJ: " + String.format("%4fm",mSkupni));
            txtMP.setText("Max: " + String.format("%4fm",maxP));

            //sBar.setProgress("%4fm",maxP);

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}