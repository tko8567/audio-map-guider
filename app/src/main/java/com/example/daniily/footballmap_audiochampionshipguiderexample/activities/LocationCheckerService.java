package com.example.daniily.footballmap_audiochampionshipguiderexample.activities;


import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.daniily.footballmap_audiochampionshipguiderexample.activities.networking.models.GuideRoute;
import com.example.daniily.footballmap_audiochampionshipguiderexample.activities.networking.models.Place;

public class LocationCheckerService extends IntentService {
    private static final String TAG = "LocationCheckerService";
    private MediaPlayer mMediaPlayer;
    private static float acceptableRadius = 100;
    LocationManager lm;

    @Override
    public void onCreate() {
        super.onCreate();
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Log.d(TAG, "service start");
    }

    public LocationCheckerService() {
        super("LocationCheckerService");
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null)
                {
                    Log.d(TAG, "Широта="+location.getLatitude());
                    Log.d(TAG, "Долгота="+location.getLongitude());
                    for (Place place : GuideRoute.get()) {

                        Location placeLocation = place.getLocation();
                        float distanceOnePoint = location.distanceTo(placeLocation);
                        Log.d(TAG, "Широта="+location.getLatitude()+" Долгота="+location.getLongitude()+" distance="+distanceOnePoint);
                        if(distanceOnePoint < acceptableRadius) {
                            Log.d(TAG, "start play");
                            int res = getResources().getIdentifier(place.getAudio(Constants.Localization.RUS), "raw", getPackageName());

                            mMediaPlayer = MediaPlayer.create(getApplicationContext(), res);

                            if (mMediaPlayer.isPlaying()) {
                                mMediaPlayer.release();
                            } else {
                                mMediaPlayer.setOnCompletionListener(mMediaPlayer -> {
                                    mMediaPlayer.release();
                                });
                                mMediaPlayer.start();
                            }

                        }

                    }


                } else {
                    Log.e(TAG, "Location is null!");
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0.1f, locationListener);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 0.1f, locationListener);


/*        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.release();
        } else {
            mMediaPlayer.setOnCompletionListener(mMediaPlayer -> {
                mMediaPlayer.release();
            });
            mMediaPlayer.start();
        }
        */



    }
}
