package com.example.daniily.footballmap_audiochampionshipguiderexample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import com.example.daniily.footballmap_audiochampionshipguiderexample.R;

/**
 * Created by daniily on 2/17/18.
 * This class shows the Google map and the path user is considered to go
 */

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onMenuClick(View v) {
        Intent mapIntent = new Intent(MenuActivity.this, MapsActivity.class);
        startActivity(mapIntent);
    }
}
