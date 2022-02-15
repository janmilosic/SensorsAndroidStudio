package com.example.senzorji_2022_4a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class maps extends AppCompatActivity {

    TextView maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        maps = findViewById(R.id.maps);

        Intent intent = getIntent();
        maps.setText(intent.getStringExtra("c"));

    }
}