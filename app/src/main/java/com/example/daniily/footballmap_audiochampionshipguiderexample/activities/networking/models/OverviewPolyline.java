package com.example.daniily.footballmap_audiochampionshipguiderexample.activities.networking.models;

import com.squareup.moshi.Json;

/**
 * Created by Dmitrii Polyakov on KENIGHACK 2018.
 */

public class OverviewPolyline {
    @Json(name = "points")
    private String points;

    public String getPoints() {
        return points;
    }
}
