package com.example.daniily.footballmap_audiochampionshipguiderexample.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class LocationTestActivity extends AppCompatActivity implements LocationListener {

    private static final String TAG = MainActivity.class.getName();
    TextView myTextView;
    LocationManager lm;

    private static float acceptableRadius = 10;
    private static double latitude = 54.7145467;
    private static double longitude = 20.52929541;

    private static Location pointOne = new Location("");
    static {
        pointOne.setLatitude(latitude);
        pointOne.setLongitude(longitude);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        TODO
        setContentView(R.layout.activity_main);
        myTextView = findViewById(R.id.coordinateText);*/

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Log.d(TAG, "ACCESS_FINE_LOCATION=" + ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) + ", ACCESS_COARSE_LOCATION=" + ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[ ] permissions = new String[2];
            permissions[0] = Manifest.permission.ACCESS_FINE_LOCATION;
            permissions[1] = Manifest.permission.ACCESS_COARSE_LOCATION;
            ActivityCompat.requestPermissions(this, permissions, 0);
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0.1f, this);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 0.1f, this);
    }

    @Override
    public void onLocationChanged(Location location)
    {
        showLocation(location);
    }

    public void showLocation(Location location) {
        Log.d(TAG, "showLocation");
        if (location != null)
        {
            Log.d(TAG, "Широта="+location.getLatitude());
            Log.d(TAG, "Долгота="+location.getLongitude());

            float distanceOnePoint = location.distanceTo(pointOne);
            myTextView.setText("Широта="+location.getLatitude()+" Долгота="+location.getLongitude()+" distance="+distanceOnePoint);
            if(distanceOnePoint < acceptableRadius) {
                Log.d(TAG, "one point");
                myTextView.setText(myTextView.getText()+" OnePoint");
            }

        } else {
            Log.e(TAG, "Location is null!");
        }
    }

    @Override
    public void onProviderDisabled(String provider)
    {
    }

    @Override
    public void onProviderEnabled(String provider)
    {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
    }

    public void checkCoorditane(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[ ] permissions = new String[2];
            permissions[0] = Manifest.permission.ACCESS_FINE_LOCATION;
            permissions[1] = Manifest.permission.ACCESS_COARSE_LOCATION;
            ActivityCompat.requestPermissions(this, permissions, 0);
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        showLocation(location);
    }
}
