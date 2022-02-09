package com.example.senzorji_2022_4a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class pospesek extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pospesek);

        tv = findViewById(R.id.tv);

        Intent intent = getIntent();
        tv.setText(intent.getStringExtra("abc"));


    }
}