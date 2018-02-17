package com.example.daniily.footballmap_audiochampionshipguiderexample.activities.networking.models;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by Dmitrii Polyakov on KENIGHACK 2018.
 */

public class Rout {
    @Json(name = "overview_polyline")
    private OverviewPolyline polyline;

    @Json(name = "legs")
    private List<Leg> legs;

    public OverviewPolyline getPolyline() {
        return polyline;
    }

    public List<Leg> getLegs() {
        return legs;
    }
}
