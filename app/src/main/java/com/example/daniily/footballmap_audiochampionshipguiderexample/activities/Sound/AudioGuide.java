package com.example.daniily.footballmap_audiochampionshipguiderexample.activities.Sound;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by qeed on 17.02.18.
 */

public class AudioGuide {

    public static void play(final Context context, final int resource) throws IOException {

        int res = resource;
        MediaPlayer player = MediaPlayer.create(context, res);
        player.start();

    }
}