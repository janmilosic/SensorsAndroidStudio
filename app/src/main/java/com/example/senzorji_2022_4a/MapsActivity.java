package com.example.senzorji_2022_4a;

import androidx.fragment.app.FragmentActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.senzorji_2022_4a.databinding.ActivityMaps2Binding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMaps2Binding binding;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(getApplicationContext(), GpsTracker.class);
        startService(intent);

        binding = ActivityMaps2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /*
     * Manipulates the map once available.
        * This callback is triggered when the map is ready to be used.
        * This is where we can add markers or lines, add listeners or move the camera. In this case,
        * we just add a marker near Sydney, Australia.
        * If Google Play services is not installed on the device, the user will be prompted to install
        * it inside the SupportMapFragment. This method will only be triggered once the user has
        * installed Google Play services and returned to the app.
        */
@Override
public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

    @Override
    protected void onResume() {
        super.onResume();
        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {

                String lokacija = (String) intent.getExtras().get("location");
                String[] ab = lokacija.split(" ");
                double lat= Double.parseDouble(ab[0]);
                double lon = Double.parseDouble(ab[1]);
                LatLng lok = new LatLng( lat, lon);
                mMap.addMarker(new MarkerOptions().position(lok).title("Marker"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(lok));

            }
        };

        registerReceiver(broadcastReceiver, new IntentFilter("Update_location"));

    }

        }
