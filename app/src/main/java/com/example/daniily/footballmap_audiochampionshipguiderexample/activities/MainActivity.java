package com.example.daniily.footballmap_audiochampionshipguiderexample.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.daniily.footballmap_audiochampionshipguiderexample.R;


/**
 * This activity is controlling the whole application.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(
                new Intent(this, MenuActivity.class)
        );

    }
}
