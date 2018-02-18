package com.example.daniily.footballmap_audiochampionshipguiderexample.networking.models;

import com.squareup.moshi.Json;

/**
 * Created by Dmitrii Polyakov on KENIGHACK 2018.
 */

class EndLocation {
    @Json(name = "lat")
    Double lat;

    @Json(name = "lng")
    Double lng;
}