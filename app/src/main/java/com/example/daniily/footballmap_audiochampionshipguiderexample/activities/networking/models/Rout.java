package com.example.daniily.footballmap_audiochampionshipguiderexample.activities.networking.models;

import com.squareup.moshi.Json;

/**
 * Created by Vesbat on 17.02.2018.
 */

public class Rout {
    @Json(name = "overview_polyline")
    private OverviewPolyline polyline;

    public OverviewPolyline getPolyline() {
        return polyline;
    }
}
