package com.example.daniily.footballmap_audiochampionshipguiderexample.activities;


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
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.kafsobor);
    }

    public LocationCheckerService() {
        super("LocationCheckerService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
//        Log.d(TAG, Integer.toString(R.raw.kafsobor));
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.release();
        } else {
            mMediaPlayer.setOnCompletionListener(mMediaPlayer -> {
                mMediaPlayer.release();
                //mMediaPlayer.reset();
            });
            mMediaPlayer.start();
        }
    }
}
