package com.example.daniily.footballmap_audiochampionshipguiderexample.activities;//package com.example.daniily.footballmap_audiochampionshipguiderexample.activities;
//
//import android.app.IntentService;
//import android.app.Service;
//import android.content.Intent;
//import android.location.Location;
//import android.os.Bundle;
//import android.os.IBinder;
//import android.support.annotation.Nullable;
//import android.util.Log;
//
//import com.example.daniily.footballmap_audiochampionshipguiderexample.R;
//import com.example.daniily.footballmap_audiochampionshipguiderexample.activities.Sound.AudioGuide;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.tasks.OnSuccessListener;
//
//import java.io.IOException;
//
///**
// * Created by qeed on 18.02.18.
// */
//
//public class LocationCheckerService extends IntentService {
//    final String TAG = "Service";
//
//    public LocationCheckerService(String name) {
//        super(name);
//    }
//    // private FusedLocationProviderClient mFusedLocationClient;
//
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate();
//        Log.d(TAG,"created");
//
//        //mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//    }
//
//    public int onStartCommand(Intent intent, int flags, int startID){
//        Log.d(TAG,"start");
//
//        try {
//            Task();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return super.onStartCommand(intent, flags, startID);
//    }
//
//    public void onDestroy(){
//        super.onDestroy();
//        AudioGuide.stop();
//        Log.d(TAG,"destroy");
//    }
//
//    public IBinder onBind(Intent intent){
//        Log.d(TAG,"bind");
//        return null;
//    }
//
//    @Override
//    protected void onHandleIntent(@Nullable Intent intent) {
//
//    }
//
//    void Task() throws IOException {
//        AudioGuide.play(this.getBaseContext(), R.raw.kafsobor);
//    }
//}


import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.daniily.footballmap_audiochampionshipguiderexample.R;

public class LocationCheckerService extends IntentService {

    private static final String TAG = "LocationCheckerService";
    private MediaPlayer mMediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.kafsobor);
    }

    public LocationCheckerService() {
        super("LocationCheckerService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //Log.d(TAG, String.valueOf(this.);
        Log.d(TAG, Integer.toString(R.raw.kafsobor));
        if (mMediaPlayer.isPlaying()){
            mMediaPlayer.release();
        }else{
            mMediaPlayer.setOnCompletionListener(mMediaPlayer -> {
                mMediaPlayer.release();
                //mMediaPlayer.reset();
            });
            mMediaPlayer.start();
        }
        //mMediaPlayer = new MediaPlayer();
        //mMediaPlayer = MediaPlayer.create(this,R.raw.kafsobor);

    }
}