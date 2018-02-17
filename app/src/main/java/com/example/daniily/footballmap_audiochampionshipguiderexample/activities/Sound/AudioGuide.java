package com.example.daniily.footballmap_audiochampionshipguiderexample.activities.Sound;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;

/**
 * Created by qeed on 17.02.18.
 */

public class AudioGuide {
    private static MediaPlayer mp = new MediaPlayer();

    public static void play(final Context baseContext ,final Activity act,final int tile){

        if (mp.isPlaying() == true){
            Log.d("AudioGuide","already playing");
        }else{
            new Handler().post(new Runnable(){
                @Override
                public void run() {
                    int resource = baseContext.getResources().getIdentifier(String.valueOf(tile),"raw",baseContext.getPackageName());
                    mp = MediaPlayer.create(act, resource);
                    mp.start();
                }
            });
        }
    }
}