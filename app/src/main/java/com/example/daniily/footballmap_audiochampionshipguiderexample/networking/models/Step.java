package com.example.daniily.footballmap_audiochampionshipguiderexample.networking.models;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.moshi.Json;

/**
 * Created by Dmitrii Polyakov on KENIGHACK 2018.
 */

public class Step {

    @Json(name = "end_location")
    EndLocation endLocation;

    public LatLng getEndLocation() {
        return new LatLng(endLocation.lat, endLocation.lng);
    }
}
