package com.example.senzorji_2022_4a;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;

import androidx.annotation.NonNull;

public class GpsTracker extends Service implements LocationListener {

    private LocationManager locationManager;
    private Context context = null;

    public GpsTracker() {

    }

    public GpsTracker(Context context){
        this.context = context;
    }

    @SuppressLint("MissingPermission")
    public Location getLokacija(){
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (isGpsEnabled){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 2, this);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return location;
        }
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getLokacija();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();

        Intent inten = new Intent("update_location");
        inten.putExtra("location", lat + " " + lon);
        sendBroadcast(inten);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}