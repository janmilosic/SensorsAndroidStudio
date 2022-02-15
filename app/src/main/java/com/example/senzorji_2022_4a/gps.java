package com.example.senzorji_2022_4a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class gps extends AppCompatActivity {

    TextView gp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        gp = findViewById(R.id.gp);

        Intent intent = getIntent();
        gp.setText(intent.getStringExtra("b"));

    }
}