package com.example.daniily.footballmap_audiochampionshipguiderexample.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.daniily.footballmap_audiochampionshipguiderexample.R;
import com.example.daniily.footballmap_audiochampionshipguiderexample.util.Route;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author tko8567
 */
public class MapsActivity2 extends AppCompatActivity {


    private static final String TAG = "MapsActivity2";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        try {
            JSONObject jsonRoute = new JSONObject(
                    getIntent().getStringExtra(Route.class.getSimpleName())
            );
            Route currentRoute = new Route(jsonRoute, this);
            Log.i(TAG, "route=" + jsonRoute);
            Log.i(TAG, "currentRoute=" + currentRoute);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
