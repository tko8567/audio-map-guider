package com.example.daniily.footballmap_audiochampionshipguiderexample.networking.models;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by Dmitrii Polyakov on KENIGHACK 2018.
 */


public class Leg {
    @Json(name = "steps")
    private List<Step> steps;

    public List<Step> getSteps() {
        return steps;
    }
}
